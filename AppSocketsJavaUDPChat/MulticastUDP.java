import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

public class MulticastUDP{
    @SuppressWarnings("deprecation")
    public static void main(String args[]){
        try {
            int port = 8080;
            InetAddress group = InetAddress.getByName("224.0.0.0");
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(group);
            Scanner scan = new Scanner(System.in);
            System.out.println("Envie un mensaje al grupo: ");
            String message = scan.nextLine();
            byte[] sendMessage = message.getBytes();
            DatagramPacket outMessage = new DatagramPacket(sendMessage, sendMessage.length, group, port);
            socket.send(outMessage);

            byte[] buffer = new byte[1024];
            String lineMessage;

            while (true) {
                DatagramPacket inMessage = new DatagramPacket(buffer, buffer.length);
                socket.receive(inMessage);
                lineMessage = new String(inMessage.getData(), 0, inMessage.getLength());
                System.out.println("Recibido: " + lineMessage);

                if (lineMessage.equalsIgnoreCase("Adios")) {
                    socket.leaveGroup(group);
                    break;
                }
                scan.close();
                socket.close();
            }
            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}