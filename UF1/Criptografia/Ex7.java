import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex7 {
    public static Connection stablishConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/uf1criptoalexarredondo",
                "root", "patata");

        return connection;
    }

    public static boolean validateUser(Connection connection, String username, String password) {
    try {
        String sql = "SELECT * FROM users WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String storedPassword = resultSet.getString("password");
            int jump = resultSet.getInt("jump");

            String encryptedInputPassword = encryptWithJump(password, jump);

            if (storedPassword.equals(encryptedInputPassword)) {
                return true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
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

public static String encryptWithJump(String password, int jump) {
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


    public static void main(String[] args) {

         try {
        Connection connection = stablishConnection();

        String username = "exampleUser";
        String password = "examplePassword";

        boolean credentialsMatch = validateUser(connection, username, password);

        if (credentialsMatch) {
            System.out.println("Credenciales válidas. El usuario puede iniciar sesión.");
        } else {
            System.out.println("Credenciales inválidas. El usuario no puede iniciar sesión.");
        }

        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
