import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Ex1 {

    public static void main(String[] args) {
        SecretKey sKey = keygenKeyGeneration();
        System.out.println("Secret Key: " + sKey);
    }

    public static SecretKey keygenKeyGeneration() {
        SecretKey sKey = null;
        String algorisme = "DES";

        try {
            KeyGenerator kGen = KeyGenerator.getInstance(algorisme);
            kGen.init(56);

            sKey = kGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al generar la clave: " + e.getMessage());
        }
        return sKey;
    }
}
