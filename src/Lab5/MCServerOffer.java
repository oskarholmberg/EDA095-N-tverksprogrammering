package Lab5;

import java.net.*;
import java.io.*;

public class MCServerOffer {

    public static void main(String args[]) {
        try {
            MulticastSocket ms = new MulticastSocket(4099);
            InetAddress ia = InetAddress.getByName("experiment.mcast.net");
            DatagramSocket ds = new DatagramSocket();
            byte[] name = InetAddress.getLocalHost().toString().getBytes();
            DatagramPacket namePacket = new DatagramPacket(name, name.length, ia, 4099);
            ms.joinGroup(ia);

            while(true) {
                byte[] buf = new byte[65536];
                DatagramPacket dp = new DatagramPacket(buf,buf.length);
                ms.receive(dp);
                String s = new String(dp.getData(),0,dp.getLength());
                System.out.println("Received: "+s);
                if(s.equals("ping")){
                    ms.send(namePacket);
                }
            }
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }

}
