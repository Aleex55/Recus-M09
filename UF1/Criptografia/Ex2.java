import java.security.MessageDigest;

public class Ex2 {
    public static void sha512(String name) {
        try {
            byte[] data = name.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(data);

            String hexa = javax.xml.bind.DatatypeConverter.printHexBinary(hash);
            System.out.println("El hash de la contrasenya " + name + " en SHA-512 es " + hexa);
        } catch (Exception e) {
            System.err.println("Error generat en la clau: " + e);
        }
    }

    public static void main(String[] args) {
        String name = "Alex Arredondo Rodriguez";
        sha512(name);
    }
}
