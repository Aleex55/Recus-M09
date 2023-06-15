package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Client {

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

            System.out.println("Cliente multicast iniciado. Esperando mensajes...");

            // Recibe mensajes del grupo multicast indefinidamente
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                socket.receive(paquete);

                String mensaje = new String(paquete.getData()).trim();
                System.out.println("Mensaje recibido: " + mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
