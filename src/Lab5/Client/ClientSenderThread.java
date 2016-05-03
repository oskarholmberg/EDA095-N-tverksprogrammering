package Lab5.Client;

import java.net.*;
import java.io.*;

public class ClientSenderThread extends Thread {
    private InetAddress ia;
    private MulticastSocket ms;


    public ClientSenderThread(InetAddress ia, MulticastSocket ms){
        this.ia=ia;
        this.ms=ms;
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
                DatagramPacket dp = new DatagramPacket(outBuf, outBuf.length, ia, 4099);
                ms.send(dp);
            }
        } catch (IOException e) {
            System.out.println("Exception:" + e);
        }
    }
}
