import java.io.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Ex8 {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        Ex8 client = new Ex8();
        client.enviarDatos();
    }

    public void enviarDatos() {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conexión establecida con el servidor");

            OutputStream outputStream = sslSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            for (int i = 0; i < 10; i++) {
                String mensaje = "Mensaje " + (i + 1);
                printWriter.println(mensaje);
                System.out.println("Mensaje enviado: " + mensaje);
                Thread.sleep(1000);
            }

            printWriter.close();
            outputStream.close();
            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
