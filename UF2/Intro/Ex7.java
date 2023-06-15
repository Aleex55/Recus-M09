import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex7 {
    public static void main(String[] args) {
        String command1 = "ping 127.0.0.1";
        String command2 = "notepad.exe";

        Thread thread1 = new Thread(() -> executeCommand(command1));
        Thread thread2 = new Thread(() -> executeCommand(command2));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

            System.out.println("Both commands executed successfully.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitValue = process.waitFor();

            if (exitValue != 0) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    System.out.println("Error: " + errorLine);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
