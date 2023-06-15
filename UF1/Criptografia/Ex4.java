import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;

public class Ex4 {
    public static void sha512(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] jumpBytes = new byte[16];
            random.nextBytes(jumpBytes);

            byte[] passwordBytes = password.getBytes();
            for (int i = 0; i < passwordBytes.length; i++) {
                passwordBytes[i] ^= jumpBytes[i % jumpBytes.length];
            }

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(passwordBytes);

            String hexa = DatatypeConverter.printHexBinary(hash);
            System.out.println("El hash SHA-512 de la contraseña " + password + " es " + hexa);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        String password = "no se que mas poner";
        sha512(password);
    }
}
