package ejercicio9;

public class Encargado implements Runnable {


    private Almacen almacen;

    public Encargado(Almacen almacen) {

        this.almacen = almacen;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(2000);
                almacen.añadirStock();

            } catch (InterruptedException e) {
                System.out.println("Se ha interrumpido el hilo mientras dormia añadiendo");
                return;
            }
        }

    }
}
