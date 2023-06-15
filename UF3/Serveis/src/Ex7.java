import org.apache.commons.net.ftp.FTPClient;

public class Ex7 {
    public static void listarArchivosYCarpetas(FTPClient ftpClient) {
        try {

            // Obtener la lista de nombres de archivos y carpetas
            String[] nombres = ftpClient.listNames();

            // Mostrar los nombres por pantalla
            if (nombres != null) {
                for (String nombre : nombres) {
                    System.out.println(nombre);
                }
            } else {
                System.out.println("No se encontraron archivos ni carpetas en el servidor FTP.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
