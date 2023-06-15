import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Ex4 {
    public static void main(String[] args) {
        ThreadGroup grupo = new ThreadGroup("GrupoHilos");

        Thread sumaThread = new Thread(grupo, new SumaHilo());
        Thread multiplicacionThread = new Thread(grupo, new MultiplicacionHilo());

        sumaThread.start();
        multiplicacionThread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
            grupo.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Suma: " + SumaHilo.getSuma());
        System.out.println("Multiplicación: " + MultiplicacionHilo.getMultiplicacion());
    }

    static class SumaHilo implements Runnable {
        private static final AtomicInteger suma = new AtomicInteger(0);

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Suma: " + suma.getAndIncrement());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public static int getSuma() {
            return suma.get();
        }
    }

    static class MultiplicacionHilo implements Runnable {
        private static final AtomicInteger multiplicacion = new AtomicInteger(1);

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Multiplicación: " + multiplicacion.updateAndGet(x -> x * 2));
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public static int getMultiplicacion() {
            return multiplicacion.get();
        }
    }
}
