import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) {
        int puerto = 8080;
        String servidor = "Localhost";
        try {
            Calculadora calculate = (Calculadora) Naming.lookup("rmi://" + servidor + ":" + puerto + "/Calculadora");

            while (true) {
                String options = JOptionPane.showInputDialog(
                    "Calcular\n" + 
                        "Suma................ (1)\n" +
                        "Resta............... (2)\n" +
                        "Multiplicacion...... (3)\n" +
                        "Division............ (4)\n" 

                );
                if (options == null){
                    break;
                }
                int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor de a"));
                int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor de b"));

                switch (options) {
                    case "1":{
                        JOptionPane.showMessageDialog(null, a + "+" + b + "=" + calculate.addition(a, b));
                        break;
                    }
                    case "2":{
                        JOptionPane.showMessageDialog(null, a + "-" + b + "=" + calculate.substraction(a, b));
                        break;
                    }
                    case "3":{
                        JOptionPane.showMessageDialog(null, a + "*" + b + "=" + calculate.multiplication(a, b));
                        break;
                    }
                    case "4":{
                        JOptionPane.showMessageDialog(null, a + "/" + b + "=" + calculate.division(a, b));
                        break;
                    }
                }

            }
            
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor:\n" + e);
        } catch(MalformedURLException e){
            JOptionPane.showMessageDialog(null, "La URL esta en formato incorrecto:\n" + e);
        }
    }
}
