package Lab5.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

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
            System.out.println("Server has received: " + s + " from " + dp.getSocketAddress());
            byte[] response = new String("30000").getBytes();
            DatagramPacket dpResp = new DatagramPacket(response, response.length, dp.getAddress(), dp.getPort());
            try {
                ms.send(dpResp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
