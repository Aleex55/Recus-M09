package Ex13;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            System.out.println("Servidor escuchando en el puerto " + PORT);

            SocketChannel clientSocketChannel = serverSocketChannel.accept();
            System.out.println("Cliente conectado: " + clientSocketChannel.getRemoteAddress());

            recibirDatos(clientSocketChannel);

            clientSocketChannel.close();
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recibirDatos(SocketChannel clientSocketChannel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder stringBuilder = new StringBuilder();

            int bytesRead;
            while ((bytesRead = clientSocketChannel.read(buffer)) != -1) {
                buffer.flip();
                String data = StandardCharsets.UTF_8.decode(buffer).toString();
                stringBuilder.append(data);
                buffer.clear();
            }

            System.out.println("Mensaje recibido: " + stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
