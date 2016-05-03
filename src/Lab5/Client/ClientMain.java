package Lab5.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientMain {
    public static void main(String[] args) {
        try {
            MulticastSocket ms = new MulticastSocket();
            ms.setTimeToLive(1);
            InetAddress ia = InetAddress.getByName("experiment.mcast.net");
            new ClientThread(ia, ms).start();

        } catch (IOException e) {
            System.out.println("Exception:" + e);
        }
    }
}
