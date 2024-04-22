package grpcholamundo.servidor;

import com.proto.saludo.SaludoServiceGrpc;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;

import io.grpc.stub.StreamObserver;

public class Servidorimpl extends SaludoServiceGrpc.SaludoServiceImplBase {
    @Override
    public void saludo (SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        //respuesta a enviar al cliente
        SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado("Hola " + request.getNombre()).build();

        //gRPC usa onNext para poder enviar la respuesta, en llamadas unitarias se llama una vez
        responseObserver.onNext(respuesta);
        
        //avisar que ha terminado
        responseObserver.onCompleted();
    }
    public void saludoStream (SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        for (int i = 0; i <= 10; i++) {
            SaludoResponse respuesta = SaludoResponse.newBuilder()
            .setResultado("Hola " + request.getNombre() + "por " + i + "Vez").build();
            responseObserver.onNext(respuesta);
        }
        responseObserver.onCompleted();
    }
}
