package grpcholamundo.servidor;

import com.proto.saludo.SaludoServiceGrpc;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;

import io.grpc.stub.StreamObserver;

public class Servidorimpl extends SaludoServiceGrpc.SaludoServiceImplBase {

    public void saludo (SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        //respuesta a enviar al cliente
        SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado("Hola " + request.getNombre()).build();

        //gRPC usa onNext para poder enviar la respuesta, en llamadas unitarias se llama una vez
        responseObserver.onNext(respuesta);
        
        //avisar que ha terminado
        responseObserver.onCompleted();
    }
    
}
