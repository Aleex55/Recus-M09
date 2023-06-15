import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Ex6 {

    public static Connection stablishConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/uf1criptoalexarredondo",
                "root", "patata");

        return connection;
    }

    public static String encryptWithJump(String password) {

        Random random = new Random();
        int jump = random.nextInt(256);

        StringBuilder jumpedPassword = new StringBuilder();
        for (char c : password.toCharArray()) {
            jumpedPassword.append((char) (c + jump));
        }

        String hashedPassword = hashWithSHA512(jumpedPassword.toString());

        StringBuilder hexadecimalPassword = new StringBuilder();
        for (byte b : hashedPassword.getBytes()) {
            hexadecimalPassword.append(String.format("%02x", b));
        }

        return hexadecimalPassword.toString();
    }

    public static void insertUser(Connection connection, String username, String encryptedPassword) {
        try {

            Random random = new Random();
            int jump = random.nextInt(256);

            String hashedPassword = hashWithSHA512(encryptedPassword);

            String sql = "INSERT INTO users (name, password, jump, hash) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, encryptedPassword);
            statement.setInt(3, jump);
            statement.setString(4, hashedPassword);

            statement.executeUpdate();
            System.out.println("User data saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String hashWithSHA512(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = messageDigest.digest(input.getBytes());

            StringBuilder hashedString = new StringBuilder();
            for (byte b : hashBytes) {
                hashedString.append(String.format("%02x", b));
            }

            return hashedString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = stablishConnection();
            String username = "exampleUser";
            String password = "examplePassword";

            String encryptedPassword = encryptWithJump(password);
            insertUser(connection, username, encryptedPassword);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
