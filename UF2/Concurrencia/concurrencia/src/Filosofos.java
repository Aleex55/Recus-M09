import java.util.concurrent.Semaphore;

public class Filosofos implements Runnable {
    private int id;
    private Semaphore[] tenedores;

    public Filosofos(int id, Semaphore[] tenedores) {
        this.id = id;
        this.tenedores = tenedores;
    }

    private void tomarTenedor(int tenedor) throws InterruptedException {
        tenedores[tenedor].acquire();
        System.out.println("Filósofo " + id + " ha tomado el tenedor " + tenedor);
    }

    private void soltarTenedor(int tenedor) {
        tenedores[tenedor].release();
        System.out.println("Filósofo " + id + " ha soltado el tenedor " + tenedor);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Filósofo " + id + " está pensando");
                Thread.sleep(2000);

                int tenedorIzq = id;
                tomarTenedor(tenedorIzq);

                int tenedorDer = (id + 1) % 5;
                tomarTenedor(tenedorDer);

                System.out.println("Filósofo " + id + " está comiendo");
                Thread.sleep(2000);

                soltarTenedor(tenedorDer);
                soltarTenedor(tenedorIzq);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Semaphore[] tenedores = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            tenedores[i] = new Semaphore(1, true);
        }

        Thread[] filosofos = new Thread[5];
        for (int i = 0; i < 5; i++) {
            Filosofos filosofo = new Filosofos(i, tenedores);
            filosofos[i] = new Thread(filosofo);
            filosofos[i].start();
        }
    }
}
