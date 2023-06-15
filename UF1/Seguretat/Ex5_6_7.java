import java.io.*;
import java.net.*;
import java.security.*;

public class Ex5_6_7 {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Tamaño de clave de 2048 bits
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();


            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor escuchando en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                recibirDatos(clientSocket);

                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recibirDatos(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String data;
            while ((data = bufferedReader.readLine()) != null) {
                System.out.println("Mensaje recibido: " + data);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}