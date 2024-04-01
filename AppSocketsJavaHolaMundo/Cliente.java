import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        int puerto = 8080;
        try {
            Socket socket = new Socket("localhost", puerto);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String lineaRecibida;
            while(!(lineaRecibida = in.readLine()).equalsIgnoreCase("Adios")){
                System.out.println("Servidor: "+ lineaRecibida);
            }

            out.println("Recepcion de datos correcta");

            out.close();
            in.close();
            socket.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
