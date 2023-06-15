import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Ex8 {
    public void listFilesInHtdocsFolder(FTPClient ftpClient) {
        try {
            FTPFile[] files = ftpClient.listFiles("/htdocs");
            System.out.println("Files in /htdocs folder:");
            for (FTPFile file : files) {
                System.out.println(file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while listing files in /htdocs folder: " + e.getMessage());
        }
    }
}
