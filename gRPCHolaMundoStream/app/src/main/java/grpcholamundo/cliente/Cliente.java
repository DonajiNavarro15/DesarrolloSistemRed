package grpcholamundo.cliente;

import com.proto.saludo.SaludoServiceGrpc;

import java.util.Iterator;

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

        saludarUnario(channel);
        saludarStream(channel);

        System.out.println("Apagando...");
        channel.shutdown();
    }

    public static void saludarUnario(ManagedChannel channel) {
        //referencia al stub
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(channel);
        //construcción de la petición enviando un parámetro
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Donaji").build();
        
    }
    public static void saludarStream(ManagedChannel channel) {
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(channel);
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Donaji").build();
        SaludoResponse respuesta = stub.saludo(peticion);
        
    }
}
