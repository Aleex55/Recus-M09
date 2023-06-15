import java.security.MessageDigest;
import java.util.Base64;
import java.util.BitSet;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Ex3 {
    public static void sha256 (String password){
        try {
            byte[] data = password.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data);
            
            BitSet bitSet = BitSet.valueOf(hash);

            KeyGenerator kGen = KeyGenerator.getInstance("AES");
            kGen.init(bitSet.length());

            SecretKey sKey = kGen.generateKey();

            System.out.println("Clau generada > " +  Base64.getEncoder().encodeToString(sKey.getEncoded()));
        } catch (Exception e) {
           System.err.println("Error generat en la clau: " + e);
        }
    }
    public static void main(String[] args) {
        String password = "patato";
        sha256(password);
    }
}
