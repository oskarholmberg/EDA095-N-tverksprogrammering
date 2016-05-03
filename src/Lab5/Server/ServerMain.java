package Lab5.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServerMain {

    public static void main(String[] args){
        try {
            MulticastSocket ms = new MulticastSocket(4099);
            InetAddress ia = InetAddress.getByName("experiment.mcast.net");
            ms.joinGroup(ia);
            new ServerReceiverThread(ms).start();
        } catch (IOException e) {
            System.out.println("Exception:" + e);
        }
    }
}
