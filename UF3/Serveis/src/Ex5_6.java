import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class Ex5_6 {
    private FTPClient ftpClient;

    public Ex5_6() {
        ftpClient = new FTPClient();
    }

    public boolean connect(String server, int port, String username, String password) {
        try {
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Failed to connect to the FTP server.");
                return false;
            }
            System.out.println("Connected to the FTP server.");
            return true;
        } catch (IOException e) {
            System.out.println("Error occurred while connecting to the FTP server: " + e.getMessage());
            return false;
        }
    }

    public void disconnect() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("Disconnected from the FTP server.");
            } catch (IOException e) {
                System.out.println("Error occurred while disconnecting from the FTP server: " + e.getMessage());
            }
        }
    }
}
