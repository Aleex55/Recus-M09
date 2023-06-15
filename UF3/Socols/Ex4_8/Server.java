package Ex4_8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    private static boolean timeoutExpired = false;
    public static void main(String[] args) {
        try {

            int port = 7777;
            ServerSocket serverSocket = new ServerSocket(port);

            Socket cliSocket = null;

            cliSocket = serverSocket.accept();

            Scanner reader = new Scanner(cliSocket.getInputStream());
            String text = reader.nextLine();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeoutExpired = true;
                }
            }, 3000);

            while (!timeoutExpired) {
                System.out.println("Client: " + text);
                System.out.flush();
                text = reader.nextLine();

                recibirMensaje(port);
            }

            timer.cancel();

            System.out.println("Servidor cerrano session");
            cliSocket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    public static void recibirMensaje(int port) throws IOException {
        
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(port)) {
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);

            String mensajeRecibido = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            String direccionIP = paqueteRecibido.getAddress().getHostAddress();
            int puertoOrigen = paqueteRecibido.getPort();

            System.out.println("Mensaje recibido: " + mensajeRecibido);
            System.out.println("IP de origen: " + direccionIP);
            System.out.println("Puerto de origen: " + puertoOrigen);
        }
    }
}

