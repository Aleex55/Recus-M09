import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ex5 {
    public static void main(String[] args) {
        listDesktopFiles();
    }

    public static void listDesktopFiles() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("cmd /c dir C:\\Users\\Alex\\Desktop");
            } else {
                process = Runtime.getRuntime().exec("ls ~/Desktop");
            }

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
