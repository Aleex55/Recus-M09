package Ex13;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
            System.out.println("Conexión establecida con el servidor");

            enviarDatos(socketChannel, "alex arredondo");

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarDatos(SocketChannel socketChannel, String mensaje) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(mensaje.getBytes(StandardCharsets.UTF_8));
            socketChannel.write(buffer);
            System.out.println("Mensaje enviado: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
