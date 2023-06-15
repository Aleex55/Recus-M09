class CalculadorFactorial2 implements Runnable {
    private int number;
    private long result;

    public CalculadorFactorial2(int number) {
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

public class Ex3 {
    private static long result1;
    private static long result2;

    public static void main(String[] args) {
        CalculadorFactorial2 calculador1 = new CalculadorFactorial2(10);
        CalculadorFactorial2 calculador2 = new CalculadorFactorial2(5);
        Cronometrador cronometrador = new Cronometrador();

        Thread thread1 = new Thread(calculador1);
        Thread thread2 = new Thread(calculador2);
        Thread timerThread = new Thread(cronometrador);

        thread1.start();
        thread2.start();
        timerThread.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cronometrador.stop();

        result1 = calculador1.getResult();
        result2 = calculador2.getResult();
        System.out.println("Res1: " + result1);
        System.out.println("Res2: " + result2);

        long sum = result1 + result2;
        System.out.println("Suma de los factoriales: " + sum);
    }
}
