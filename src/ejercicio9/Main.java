package ejercicio9;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Producto pro1 = new Producto(1);
        Producto pro2 = new Producto(2);
        Producto pro3 = new Producto(3);
        List<Producto> productos = List.of(pro1, pro2, pro3);
        Almacen almacen = new Almacen(productos);
        Thread encargado = new Thread(new Encargado(almacen));
        Thread producto1 = new Thread(new Consultar(almacen, pro1), "producto 1");
        Thread producto2 = new Thread(new Consultar(almacen, pro2), "producto 2");
        Thread producto3 = new Thread(new Consultar(almacen, pro3), "producto 3");
        Thread[] hilos= {producto1,producto2,producto3};

        encargado.start();


        for (int i = 0; i < hilos.length ; i++) {
            hilos[i].start();
        }

        Thread.sleep(15000);
        for (int i = 0; i < hilos.length ; i++) {
            hilos[i].interrupt();
        }
        encargado.interrupt();
        for (int i = 0; i < hilos.length ; i++) {
            hilos[i].join();
        }
        encargado.join();
    }
}
