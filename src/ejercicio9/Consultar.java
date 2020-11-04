package ejercicio9;

public class Consultar implements Runnable {


    private Almacen almacen;
    private Producto producto;

    public Consultar(Almacen almacen, Producto producto) {

        this.almacen = almacen;
        this.producto = producto;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                almacen.consultarStock(producto.getId());

            } catch (InterruptedException e) {
                System.out.println("Se ha interrumpido el hilo mientras dormia consultando");
                return;
            }

        }


    }
}
