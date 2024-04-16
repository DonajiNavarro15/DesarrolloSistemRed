import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            int puerto = 8080;
            String servidor = "Localhost";

            System.out.println("Iniciando serividor en: ");
            System.out.println("Hostname: " + servidor);
            System.out.println("Puerto: " + puerto);

            Registry registrer = LocateRegistry.createRegistry(puerto);
            System.setProperty("java.rmi.server.hostname", servidor);
            registrer.rebind("Calculadora", new CalculadoraRMI());

            System.out.println("Servidor en ejecución en espera de clientes...");
        } catch (RemoteException e) {
            System.err.println(e);
        }
    }
}
