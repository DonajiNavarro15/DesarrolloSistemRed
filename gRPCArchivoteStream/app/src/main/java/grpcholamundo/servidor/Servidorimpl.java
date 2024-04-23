package grpcholamundo.servidor;


import filetransfer.Holamundo.FileData;
import filetransfer.Holamundo.FileRequest;
import io.grpc.stub.StreamObserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Servidorimpl extends SaludoServiceGrpc.SaludoServiceImplBase {
    @Override
    public void transferFile(FileRequest request, StreamObserver<FileData> responseObserver) {
        String filename = request.getFilename();
        try (InputStream inputStream = Servidor.class.getResourceAsStream("/" + filename);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                FileData fileData = FileData.newBuilder().setContent(line).build();
                responseObserver.onNext(fileData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }

        responseObserver.onCompleted();
    }
}
