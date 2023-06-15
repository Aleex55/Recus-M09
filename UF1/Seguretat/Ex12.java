import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Ex12 {
    public static void main(String[] args) {
        mostrarCipherSuites();
    }

    public static void mostrarCipherSuites() {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket();

            String[] cipherSuites = sslSocket.getEnabledCipherSuites();
            for (String cipherSuite : cipherSuites) {
                System.out.println("Cipher Suite: " + cipherSuite);
            }

            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
