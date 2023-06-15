class CalculadorFactorial  implements Runnable {
    private int number;
    
    public CalculadorFactorial (int number) {
        this.number = number;
    }
    
    public void run() {
        long factorial = calculateFactorial(number);
        System.out.println("Factorial of " + number + " = " + factorial);
    }
    
    private long calculateFactorial(int n) {
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}

public class Ex1 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new CalculadorFactorial(10));
        Thread thread2 = new Thread(new CalculadorFactorial(5));
        
        thread1.start();
        thread2.start();
    }
}