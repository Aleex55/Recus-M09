import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Ex9 {
    public static void main(String[] args) {
        try {
            String password = "MySecretPassword";
            String privateKey = "MyPrivateKey";
            
            String encryptedPassword = encryptPassword(password, privateKey);
            System.out.println("Encrypted Password: " + encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptPassword(String password, String privateKey) throws Exception {
        SecretKeySpec secretKey = generateKey(privateKey);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static SecretKeySpec generateKey(String privateKey) throws Exception {
        byte[] privateKeyBytes = privateKey.getBytes();
        byte[] keyBytes = new byte[16];
        for (int i = 0; i < privateKeyBytes.length && i < keyBytes.length; i++) {
            keyBytes[i] = privateKeyBytes[i];
        }
        return new SecretKeySpec(keyBytes, "AES");
    }
}
