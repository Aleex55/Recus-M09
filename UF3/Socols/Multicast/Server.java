package Multicast;
import java.io.IOException;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
            // Crea un grupo de multicast en la dirección y el puerto especificados
            InetAddress grupo = InetAddress.getByName("239.0.0.1");
            int puerto = 8888;

            // Crea un socket multicast
            MulticastSocket socket = new MulticastSocket(puerto);

            NetworkInterface networkInterface = NetworkInterface.getByName("lo");

            // Une el socket al grupo de multicast en la interfaz de red
            socket.joinGroup(new InetSocketAddress(grupo, puerto), networkInterface);

            System.out.println("Servidor multicast iniciado. Esperando conexiones...");

            // Envía mensajes periódicamente al grupo multicast
            while (true) {
                String mensaje = "Hola clientes!";
                byte[] buffer = mensaje.getBytes();

                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                socket.send(paquete);

                System.out.println("Mensaje enviado: " + mensaje);

                // Espera un segundo antes de enviar el siguiente mensaje
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
