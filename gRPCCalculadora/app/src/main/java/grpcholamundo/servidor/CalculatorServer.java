package grpcholamundo.servidor;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
public class CalculatorServer {
   public static void main(String[] args) throws IOException, InterruptedException {
       Server server = ServerBuilder.forPort(8080)
               .addService(new CalculatorServiceImpl())
               .build();

       server.start();
       System.out.println("Calculator gRPC Server started");
       server.awaitTermination();
   }
}