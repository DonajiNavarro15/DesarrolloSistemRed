package grpcholamundo.servidor;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Servidor {
    public static void main(String[] args) throws IOException, InterruptedException{
        //establece el puerto
        int puerto = 8080;

        //creacion de gRPC implementando el proto
        Server servidor = ServerBuilder
            .forPort(puerto)
            .addService(new Servidorimpl())
            .build();
        //iniciar servidor
        servidor.start();
        System.out.println("Servidor iniciado... ");
        System.out.println("Escuchando en el puerto; " + puerto);

        //al recibir la solicitud de apagado
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Recibiendo solicitud de apagado");
                servidor.shutdown();
                System.out.println("Servidor detenido");
            } 
        });

        //espera a que se cierren las conexiones
        servidor.awaitTermination();
    }
}
