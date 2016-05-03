package Lab5.Client;

import java.net.*;
import java.io.*;

public class ClientThread extends Thread {
    private InetAddress ia;
    private MulticastSocket ms;


    public ClientThread(InetAddress ia, MulticastSocket ms) {
        this.ia = ia;
        this.ms = ms;
    }

    public void run() {
        try {
            while (true) {
                int ch;
                String s = new String();
                do {
                    ch = System.in.read();
                    if (ch != '\n') {
                        s = s + (char) ch;
                    }
                } while (ch != '\n');
                System.out.println("Sending message: " + s);
                byte[] outBuf = s.getBytes();
                DatagramPacket dpOut = new DatagramPacket(outBuf, outBuf.length, ia, 4099);
                ms.send(dpOut);
                byte[] buf = new byte[65536];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                try {
                    ms.receive(dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String resp = new String(dp.getData(), 0, dp.getLength());
                System.out.println("Received: " + resp + " from " + dp.getSocketAddress());
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket dp2 = new DatagramPacket(outBuf, outBuf.length, dp.getAddress(), Integer.valueOf(resp));
                System.out.println("Sending request to TimeServer...");
                ds.send(dp2);
                System.out.println("Request sent.");
                byte[] buf3 = new byte[65536];
                DatagramPacket dp3 = new DatagramPacket(buf3, buf3.length);
                try {
                    ds.receive(dp3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String resp3 = new String(dp3.getData(), 0, dp3.getLength());
                System.out.println("Received: " + resp3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
