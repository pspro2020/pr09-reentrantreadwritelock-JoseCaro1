package ejercicio9;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Almacen {


    private final List<Producto> productos;
    private List<Integer> productosId = new ArrayList<>();
    private final ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();
    Random random = new Random();

    public Almacen(List<Producto> productos) {
        this.productos = productos;
    }

    protected int consultarStock(int productId) {
        int count = 0;
        readLock.lock();
        try {
            for (Integer id : productosId) {
                if (id.equals(productId)) {
                    count++;
                }
            }
        } finally {
            readLock.unlock();
        }
        System.out.printf("Hay %d del %s en stock\n", count, Thread.currentThread().getName());
        return count;
    }

    protected int añadirStock() {
        Producto productoAdd = productos.get(random.nextInt(productos.size()));
        writeLock.lock();
        try {
            productosId.add(productoAdd.getId());
        } finally {
            writeLock.unlock();
        }
        System.out.printf("Se ha añadido el producto con el id %d\n", productoAdd.getId());
        return productoAdd.getId();


    }

}
