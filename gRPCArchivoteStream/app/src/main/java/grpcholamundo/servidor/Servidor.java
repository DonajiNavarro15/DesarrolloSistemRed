package grpcholamundo.servidor;

import java.io.IOException;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Servidor {
    public static void main(String[] args) throws Exception {
        int puerto = 8080;

        Server servidor = ServerBuilder.forPort(puerto)
                .addService((BindableService) new ServidorImpl())
                .build();

        servidor.start();

        System.out.println("Servidor iniciado en el puerto: " + puerto);

        // Esperar a que el servidor sea apagado
        servidor.awaitTermination();
    }
}