import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.InetAddress;

public class SocketUdp {
    
    public static void main() throws IOException{
        byte[] missatge = "Patata".getBytes();

        InetAddress multicastIP = InetAddress.getByName("localhost");
    
        int portDesti = 5555;

        DatagramPacket packetSend = new DatagramPacket(missatge, missatge.length, multicastIP, portDesti);

        byte[] recievedData = new byte[1024];

        DatagramPacket packetRecieved = new DatagramPacket(recievedData, recievedData.length);
        
        try (MulticastSocket socket = new MulticastSocket(5555)) {

            socket.send(packetSend);
            
            socket.receive(packetRecieved);

            System.out.println("S'ha rebut un paquet de " + packetRecieved.getSocketAddress());
            String recievedMessage = new String(packetRecieved.getData());

            System.out.println(recievedMessage);

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