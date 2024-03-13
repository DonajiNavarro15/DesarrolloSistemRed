
package Ejemplo2.EjerciciosHilosConJava;

public class Secuencias implements Runnable{
    public static void main(String[] args){
        Thread hiloUno = new Thread(new Secuencias(), "Hilo 1:");
        for(int i = 1; i<=100; i++){
            System.out.println("Hilo1:"+ i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hiloUno.start();

        Thread hiloDos = new Thread(new Secuencias(), "Hilo 2:");
        for(int i = 100; i>=1; i--){
            System.out.println("Hilo2:"+ i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hiloDos.start();
        
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    } 
}
