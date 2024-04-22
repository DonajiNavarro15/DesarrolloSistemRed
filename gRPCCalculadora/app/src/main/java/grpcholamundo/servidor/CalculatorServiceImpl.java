package grpcholamundo.servidor;

import com.example.calculator.CalculatorProto.CalculationRequest;
import com.example.calculator.CalculatorProto.CalculationResponse;
import com.example.calculator.CalculatorServiceGrpc;

import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void addition(CalculationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        int result = request.getA() + request.getB();
        CalculationResponse response = CalculationResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void substraction(CalculationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        int result = request.getA() - request.getB();
        CalculationResponse response = CalculationResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void multiplication(CalculationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        int result = request.getA() * request.getB();
        CalculationResponse response = CalculationResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void division(CalculationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        if (request.getB() == 0) {
            responseObserver.onError(new IllegalArgumentException("Cannot divide by zero"));
            return;
        }
        int result = request.getA() / request.getB();
        CalculationResponse response = CalculationResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

