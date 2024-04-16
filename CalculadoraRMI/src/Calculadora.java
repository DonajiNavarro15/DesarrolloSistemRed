import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote{

    public int division(int a, int b) throws RemoteException;
    public int multiplication(int a, int b) throws RemoteException;
    public int addition(int a, int b) throws RemoteException;
    public int substraction(int a, int b) throws RemoteException;
} 