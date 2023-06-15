class CalculadorFactorial implements Runnable {
    private int number;
    private long result;

    public CalculadorFactorial(int number) {
        this.number = number;
    }

    public void run() {
        result = calcularFactorial(number);
    }

    private long calcularFactorial(int n) {
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public long getResult() {
        return result;
    }
}

class Cronometrador implements Runnable {
    private boolean running;

    public void run() {
        running = true;
        long startTime = System.nanoTime();

        while (running) {
            
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo transcurrido: " + elapsedTime + " nanosegundos");
    }

    public void stop() {
        running = false;
    }
}

public class Ex2 {
    private static long result1;
    private static long result2;

    public static void main(String[] args) {
        CalculadorFactorial calculador1 = new CalculadorFactorial(10);
        CalculadorFactorial calculador2 = new CalculadorFactorial(5);

        Thread thread1 = new Thread(calculador1);
        Thread thread2 = new Thread(calculador2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result1 = calculador1.getResult();
        result2 = calculador2.getResult();

        long sum = result1 + result2;
        System.out.println("Sum of factorials: " + sum);
    }
}
