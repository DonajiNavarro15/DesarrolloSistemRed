package servidor;
import java.io.IOException;
import java.io.InputStream;

import com.google.protobuf.ByteString;
import com.proto.audio.AudioServiceGrpc;
import com.proto.audio.Audio.DataChunkResponse;
import com.proto.audio.Audio.DownloadFileRequest;

import io.grpc.stub.StreamObserver;

public class ServidorImpl extends AudioServiceGrpc.AudioServiceImplBase {
    public void downloadAudio(DownloadFileRequest request, StreamObserver<DataChunkResponse> respObserver) {
        String archivoNombre = "/" + request.getNombre();
        System.out.println("\n\nEnviando el archivo: " + request.getNombre());
        InputStream filStream = ServidorImpl.class.getResourceAsStream(archivoNombre);
        
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int lenght;
        try {
            while ((lenght = filStream.read(buffer, 0, bufferSize)) != -1) {
                DataChunkResponse respuesta = DataChunkResponse.newBuilder()
                .setData(ByteString.copyFrom(buffer, 0, lenght))
                .build();
                System.out.println(".");
                respObserver.onNext(respuesta);
            }
            filStream.close();
        } catch (IOException e) {
            System.out.println("No se pudo obtener el archivo");
        }
        respObserver.onCompleted();
    }
}
