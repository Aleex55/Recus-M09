import java.util.concurrent.Semaphore;

public class Ex1_2 {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Semaphore semaphore = new Semaphore(1, true);

        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    semaphore.acquire();

                    monitor.openReading();
                    System.out.println("Thread 1 is reading...");
                    Thread.sleep(2000);
                    monitor.closeReading();
                    System.out.println("Thread 1 closed reading...");

                    monitor.openWriting();
                    System.out.println("Thread 1 is writing...");
                    Thread.sleep(2000);
                    monitor.closeWriting();
                    System.out.println("Thread 1 closed writing...");

                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    semaphore.acquire();
                    monitor.openReading();
                    System.out.println("Thread 2 is reading...");
                    Thread.sleep(2000);
                    monitor.closeReading();
                    System.out.println("Thread 2 closed reading...");

                    Thread.sleep(5000);

                    monitor.openWriting();
                    System.out.println("Thread 2 is writing...");
                    Thread.sleep(2000);
                    System.out.println("Thread 2 closed writing...");
                    monitor.closeWriting();

                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
    }
}