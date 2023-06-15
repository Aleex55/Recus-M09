import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class ex8 {
    public static void main(String[] args) {
        KeyPair keyPair = generateKeyPair();
        
        if (keyPair != null) {
            System.out.println("Key pair generated successfully:");
            System.out.println("Public Key: " + keyPair.getPublic());
            System.out.println("Private Key: " + keyPair.getPrivate());
        } else {
            System.out.println("Failed to generate key pair.");
        }
    }
    
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyGen.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
