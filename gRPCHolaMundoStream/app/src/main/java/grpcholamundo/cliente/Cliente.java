package grpcholamundo.cliente;

import com.proto.saludo.SaludoServiceGrpc;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 8080;

        ManagedChannel channel = ManagedChannelBuilder
            .forAddress(host, puerto)
            .usePlaintext()
            .build();
        saludoUnitario(channel);
        saludarStream(channel);
        System.out.println("Apagando...");
        channel.shutdown();

    }
    public static void saludoUnitario(ManagedChannel channel) {
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(channel);
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Donaji").build();
        SaludoResponse respuesta = stub.saludo(peticion);
        System.out.println("Respuesta RPC: " + respuesta.getResultado());

    }
    public static void saludarStream(ManagedChannel channel) {
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(channel);
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Donaji").build();
        stub.saludoStream(peticion).forEachRemaining(respuesta -> {
            System.out.println("Respuesta RPC: " + respuesta.getResultado());
        });
    }
}
