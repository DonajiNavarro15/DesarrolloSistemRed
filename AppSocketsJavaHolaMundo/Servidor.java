import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{
    public static void main(String[] args) {
        int port = 8080;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor escuchando en el puerto: "+ port + "...");
            Socket socket = serverSocket.accept();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Hola");
            out.println("Mundo");
            out.println("Desde el servidor!");
            out.println("Adios");

            System.out.println("Cliente: " + in.readLine());
            out.close();
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}