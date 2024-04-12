import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MulticastChatGUI {
    private static final String MULTICAST_ADDRESS = "224.0.0.0";
    private static final int PORT = 8080;

    private static MulticastSocket socket;
    private static InetAddress group;

    private static String nombreUsuario;

    private static JTextArea chatArea;
    private static JTextField messageField;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        try {
            socket = new MulticastSocket(PORT);
            group = InetAddress.getByName(MULTICAST_ADDRESS);
            socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        JFrame nameFrame = new JFrame("Ingrese su nombre");
        nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nameFrame.setSize(300, 100);
        nameFrame.setLayout(new BorderLayout());

        JTextField nameField = new JTextField();
        JButton enterButton = new JButton("Entrar");

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nombreUsuario = nameField.getText();
                nameFrame.setVisible(false);
                createChatWindow();
            }
        });

        nameFrame.add(new JLabel("Nombre: "), BorderLayout.WEST);
        nameFrame.add(nameField, BorderLayout.CENTER);
        nameFrame.add(enterButton, BorderLayout.SOUTH);
        nameFrame.setVisible(true);
    }

    @SuppressWarnings("deprecation")
    private static void createChatWindow() {
        JFrame chatFrame = new JFrame("Chat Multicast");
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatFrame.setSize(400, 300);
        chatFrame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        chatFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());

        messageField = new JTextField();
        messagePanel.add(messageField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        messagePanel.add(sendButton, BorderLayout.EAST);

        chatFrame.add(messagePanel, BorderLayout.SOUTH);
        chatFrame.setVisible(true);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket inMessage = new DatagramPacket(buffer, buffer.length);
                    socket.receive(inMessage);
                    String lineMessage = new String(inMessage.getData(), 0, inMessage.getLength());
                    chatArea.append(lineMessage + "\n");

                    if (lineMessage.trim().equalsIgnoreCase("Adios")) {
                        socket.leaveGroup(group);
                        socket.close();
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("deprecation")
    private static void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            String formattedMessage = nombreUsuario + ": " + message;
            byte[] sendMessage = formattedMessage.getBytes();
            DatagramPacket outMessage = new DatagramPacket(sendMessage, sendMessage.length, group, PORT);
            try {
                socket.send(outMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageField.setText("");

            if (message.equalsIgnoreCase("Adios")) {
                try {
                    socket.leaveGroup(group);
                    socket.close();
                    System.exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
