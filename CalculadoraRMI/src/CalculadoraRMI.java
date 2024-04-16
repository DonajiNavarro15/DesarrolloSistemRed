import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraRMI extends UnicastRemoteObject implements Calculadora {
    protected CalculadoraRMI() throws RemoteException{
        super();
    }

    @Override
    public int division(int a, int b) throws RemoteException {
        return a / b;
    }

    @Override
    public int multiplication(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public int addition(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int substraction(int a, int b) throws RemoteException {
        return a - b;
    }
}
