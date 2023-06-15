import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex6 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.print("Comanda: ");
            String comanda = reader.readLine();
            
            Process proceso = Runtime.getRuntime().exec(comanda);
            
            BufferedReader procesoOutput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            
            String linea;
            System.out.println("Resultados:");
            while ((linea = procesoOutput.readLine()) != null) {
                System.out.println(linea);
            }
            
            procesoOutput.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
