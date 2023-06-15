import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ex1 {
    public static void main(String[] args) {
        String nombreArchivo = "Algo/Hola.txt"; 
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    /* 
     * java -Djava.security.manager Ex

    */
}