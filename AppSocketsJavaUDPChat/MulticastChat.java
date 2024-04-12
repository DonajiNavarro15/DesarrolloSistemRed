import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MulticastChat {
    @SuppressWarnings("deprecation")
    public static void main(String args[]) {
        try {
            int port = 8080;
            InetAddress group = InetAddress.getByName("224.0.0.0");
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(group);

            Thread receiverThread = new Thread(new Receiver(socket, group));
            receiverThread.start();

            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese su nombre:");
            String nombre = scan.nextLine();
            System.out.println("Bienvenido al chat, " + nombre + "! Envie un mensaje al grupo o escriba 'Adios' para salir:");

            while (true) {
                String message = scan.nextLine();
                String mensajeConNombre = nombre + ": " + message;
                byte[] sendMessage = mensajeConNombre.getBytes();
                DatagramPacket outMessage = new DatagramPacket(sendMessage, sendMessage.length, group, port);
                socket.send(outMessage);

                if (message.equalsIgnoreCase("Adios")) {
                    break;
                }
            }

            socket.leaveGroup(group);
            socket.close();
            scan.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class Receiver implements Runnable {
    private MulticastSocket socket;
    private InetAddress group;

    public Receiver(MulticastSocket socket, InetAddress group) {
        this.socket = socket;
        this.group = group;
    }

    public void run() {
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket inMessage = new DatagramPacket(buffer, buffer.length);
                socket.receive(inMessage);
                String lineMessage = new String(inMessage.getData(), 0, inMessage.getLength());
                System.out.println(lineMessage);
            }
        } catch (IOException e) {
            System.out.println("EL chat ha terminado, vuelve pronto: " + e.getMessage());
        }
    }
}
