package Lab5.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ServerThread extends Thread{

    private MulticastSocket ms;

    public ServerThread(MulticastSocket ms) {
        this.ms = ms;
    }

    public void run() {
        while (true) {
            byte[] buf = new byte[65536];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            try {
                ms.receive(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String s = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Received: " + s + " from " + dp.getSocketAddress());
        }
    }
}
