package cliente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.

UnsupportedAudioFileException;

import com.proto.audio.Audio.DownloadFileRequest;
import com.proto.audio.AudioServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
    String host = "localhost";
    int puerto = 8080;
    String nombre;
    ManagedChannel ch = ManagedChannelBuilder.forAddress(host, puerto).usePlaintext().build();

    nombre = "sample.wav";
    streamWav(ch, nombre, 44100f);

    nombre = "sample.mp3";
    ByteArrayInputStream streamMp3 = downloadFile(ch, nombre);
    playMp3(streamMp3, nombre);
    try {
        streamMp3.close();
    } catch (IOException e) {
        System.out.println("error");
    }
    nombre = "sample.wav";
    ByteArrayInputStream streamWav = downloadFile(ch, nombre);
    playWav(streamMp3, nombre);
    try {
        streamWav.close();
    } catch (IOException e) {
        System.out.println("error");
    }


    System.out.println("Apagado");
    ch.shutdown();
    }
    public static void streamWav(ManagedChannel ch, String nombre, float simpleRate){
        try {
            AudioFormat newFormat = new AudioFormat(simpleRate, 16, 2, true, false);
            SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(newFormat);
            sourceDataLine.open(newFormat);
            sourceDataLine.start();

            AudioServiceGrpc.AudioServiceBlockingStub stub = AudioServiceGrpc.newBlockingStub(ch);
            DownloadFileRequest peticion = DownloadFileRequest.newBuilder().setNombre(nombre).build();

            int bufferSize = 1024;
            System.out.println("Reproduciendo");
            stub.downloadAudio(peticion).forEachRemaining(respuesta -> {
                try {
                    sourceDataLine.write(respuesta.getData().toByteArray(), 0, bufferSize);
                } catch (Exception e) {
                    System.out.println("error");
                }
            });
            System.out.println("recepcion de datos correcta");
            System.out.println("fin");

            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

        public static ByteArrayInputStream downloadFile (ManagedChannel ch, String nombre){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            AudioServiceGrpc.AudioServiceBlockingStub stub = AudioServiceGrpc.newBlockingStub(ch);
            DownloadFileRequest peticion = DownloadFileRequest.newBuilder().setNombre(nombre).build();

            System.out.println("Recibiendo");
            stub.downloadAudio(peticion).forEachRemaining(respuesta -> {
                try {
                    stream.write(respuesta.getData().toByteArray());
                    System.out.println(".");
                } catch (IOException e) {
                    System.out.println("Error");
                }
            });

            System.out.println("Correcto");
            return new ByteArrayInputStream(stream.toByteArray());
    }

    public static void playWav(ByteArrayInputStream inStream, String nombre){
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(inStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("reproduciendo");
            clip.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        } catch (UnsupportedAudioFileException | IOException e) {
            System.out.println("error");
        }catch (LineUnavailableException e){
            System.out.println("error");
        }
    }
    public static void playMp3 (ByteArrayInputStream inStream, String nombre){
        try {
            System.out.println("Recibiendo");
            
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
