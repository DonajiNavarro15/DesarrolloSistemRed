import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int port = 8080;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor escuchando en el puerto: " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + socket.getInetAddress());

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                String message;
                while ((message = consoleInput.readLine()) != null && !message.equalsIgnoreCase("Adios")) {
                    out.println(message);
                }

                out.close();
                in.close();
                socket.close();
                if (message.equalsIgnoreCase("Adios")) {
                    break;
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}