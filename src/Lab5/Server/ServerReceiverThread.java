package Lab5.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ServerReceiverThread extends Thread{

    private MulticastSocket ms;

    public ServerReceiverThread(MulticastSocket ms) {
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
            try {
                String response = new TimeServer().getResponse(s);
                DatagramSocket ds = new DatagramSocket(dp.getPort(), dp.getAddress());
                byte[] respBuf = response.getBytes();
                DatagramPacket dpResp = new DatagramPacket(respBuf, respBuf.length);
                ds.send(dpResp);

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
