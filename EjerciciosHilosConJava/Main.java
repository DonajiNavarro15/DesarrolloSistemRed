package Ejemplo2.EjerciciosHilosConJava;
public class Main {
    public static void main(String[] args) {
        nombres secuenciaHola = new nombres("Hola");
        nombres secuenciaMundo = new nombres("Mundo");

        Thread hiloUno = new Thread(secuenciaHola, "Hilo 1");
        Thread hiloDos = new Thread(secuenciaMundo, "Hilo 2");

        hiloUno.start();
        hiloDos.start();
    }
   
}
class nombres implements Runnable {
    private String mensaje;

    public nombres(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ": " + mensaje);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}







   

