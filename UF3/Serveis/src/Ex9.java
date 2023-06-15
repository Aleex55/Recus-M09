import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;

public class Ex9 {
    public static void readFixer(FTPClient ftpClient) throws IOException{
        String remoteFilePath = "/htdocs/fixa.txt";

        InputStream inputStream = ftpClient.retrieveFileStream(remoteFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }
}
