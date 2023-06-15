import java.net.*;
import java.util.Enumeration;

public class NetworkInterfaceExample {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            System.out.println(networkInterface.getName());
        }
    }
}
