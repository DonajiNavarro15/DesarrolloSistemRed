/*public class Programa1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Subproceso corriendo...");
      
    }

    public static void main(String[] args){
        Thread hilo = new Thread(new Programa1(), "Hilo1");
        hilo.start();
        System.out.println("Este codigo esta fuera del hilo");
    }   
}*/

public class Programa1 extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Subproceso corriendo...");
      
    }

    public static void main(String[] args){
        Programa1 hilo = new Programa1();
        hilo.start();

        System.out.println(Thread.currentThread().getName() + "Este codigo esta fuera del hilo...");
    }
}