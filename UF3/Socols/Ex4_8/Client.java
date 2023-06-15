package Ex4_8;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        

        try {
            Socket client_socket = new Socket(InetAddress.getByName("localhost"), 7777);

            PrintStream writer = new PrintStream(client_socket.getOutputStream());

            Scanner kb = new Scanner(System.in);
            String text = "";

            while (!text.contains("/")) {
                
                System.out.println("Envía un mensaje");

                text = kb.nextLine();

                writer.println(text);
                writer.flush();

                enviarMensaje(text, "7777");
                
            }

            kb.close();
            writer.flush();
            client_socket.close();
        } catch (Exception e) {
            
        }
    }

    public static void enviarMensaje(String mensaje, String ipDestino) throws IOException {
        int puertoDestino = 5555;

        InetAddress direccionDestino = InetAddress.getByName(ipDestino);
        byte[] datosEnvio = mensaje.getBytes();

        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionDestino, puertoDestino);

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(paqueteEnvio);
            System.out.println("Mensaje enviado correctamente a " + ipDestino);
        }
    }
}
