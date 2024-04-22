package grpcholamundo.cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.example.calculator.CalculatorProto.*;
import com.example.calculator.CalculatorServiceGrpc;

public class CalculatorClientGUI {
    private JTextField numField1, numField2, resultField;
    private JComboBox<String> operationComboBox;

    public CalculatorClientGUI() {
        JFrame frame = new JFrame("Calculator Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        JLabel numLabel1 = new JLabel("Number 1:");
        numField1 = new JTextField();
        JLabel numLabel2 = new JLabel("Number 2:");
        numField2 = new JTextField();
        JLabel operationLabel = new JLabel("Operation:");
        operationComboBox = new JComboBox<>(new String[]{"Addition", "Subtraction", "Multiplication", "Division"});
        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Result:");
        resultField = new JTextField();
        resultField.setEditable(false);

        frame.add(numLabel1);
        frame.add(numField1);
        frame.add(numLabel2);
        frame.add(numField2);
        frame.add(operationLabel);
        frame.add(operationComboBox);
        frame.add(calculateButton);
        frame.add(resultLabel);
        frame.add(resultField);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private void calculate() {
        String serverAddress = "localhost";
        int serverPort = 8080;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        int num1 = Integer.parseInt(numField1.getText());
        int num2 = Integer.parseInt(numField2.getText());
        String operation = (String) operationComboBox.getSelectedItem();

        CalculationRequest request = CalculationRequest.newBuilder()
                .setA(num1)
                .setB(num2)
                .build();

        CalculationResponse response;
        switch (operation) {
            case "Addition":
                response = stub.addition(request);
                break;
            case "Subtraction":
                response = stub.substraction(request);
                break;
            case "Multiplication":
                response = stub.multiplication(request);
                break;
            case "Division":
                response = stub.division(request);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }

        resultField.setText(Integer.toString(response.getResult()));

        channel.shutdown();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorClientGUI();
            }
        });
    }
}
