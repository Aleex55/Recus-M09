import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class App {
    public static void main(String[] args) throws Exception {

        FTPClient client = new FTPClient();
        conexion(client, "ftpupload.net", "epiz_33485003", "sHOQXFJaZK6gAf");
        desconexion(client);
       
    }
    public static void conexion (FTPClient client, String SERVER, String USER, String PASSWORD){
        try {
            client.connect(SERVER);
            boolean login = client.login(USER, PASSWORD);

            if (login) {
                System.out.println("Connectado");
            }else{
                System.out.println("Algo fue mal");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void desconexion (FTPClient client){
        try {
            client.disconnect();
            System.out.println("Desconectado");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
