import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class Ex10 {
    public static void fileUpload(FTPClient ftpClient, String filePath) throws IOException{
        File localFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(localFile);

        // Cambiar al directorio remoto
        ftpClient.changeWorkingDirectory("/htdocs");

        // Subir el archivo al servidor FTP
        boolean uploaded = ftpClient.storeFile(localFile.getName(), inputStream);
        inputStream.close();

        if (uploaded) {
            System.out.println("¡Archivo subido con éxito!");
        } else {
            System.out.println("No se pudo subir el archivo.");
        }
    }
}
