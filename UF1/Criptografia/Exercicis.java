import java.util.Base64;
import java.util.Scanner;

public class Exercicis{
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        

        System.out.println("Clau DES generada > " + Base64.getEncoder().encodeToString(Ex1.keygenKeyGeneration().getEncoded()));

        System.out.print("Contrasenya > ");
        String password = kb.next();
        Ex2.sha512(password);

        System.out.println();

        Ex3.sha256(password);

        kb.close();
    }
}