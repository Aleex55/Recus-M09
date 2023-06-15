
public class Ex5 {


    private static Object lock = new Object();
    private static int currentIndex = 0;
    public static void main(String[] args) {
        char[] letras = new char[26];
        char letra = 'a';

        for (int i = 0; i < 26; i++) {
            letras[i] = letra;
            letra++;
        }

        Thread hilo1 = new Thread(() -> {
            while (currentIndex <= 25) {
                synchronized (lock) {
                    System.out.println("Thread1: " + letras[currentIndex]);
                    currentIndex++;
                    lock.notify();
                    if (currentIndex <= 25) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread hilo2 = new Thread(() -> {
            while (currentIndex <= 25) {
                synchronized (lock) {
                    System.out.println("Thread2: " + letras[currentIndex]);
                    currentIndex++;
                    lock.notify();
                    if (currentIndex <= 25) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        hilo1.start();
        hilo2.start();
    }


}
