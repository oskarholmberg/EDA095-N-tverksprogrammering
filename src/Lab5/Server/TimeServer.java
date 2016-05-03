package Lab5.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;

public class TimeServer extends Thread {

    public TimeServer() {

    }

    public void run() {
        while (true) {
            try {
                DatagramSocket ds = new DatagramSocket(30000);
                byte[] buf = new byte[65536];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);
                String resp = new String(dp.getData(), 0, dp.getLength());
                System.out.println("TimeServer has received: " + resp + " from " + dp.getSocketAddress());
                byte[] outBuf = getResponse(resp).getBytes();
                DatagramPacket dpOut = new DatagramPacket(outBuf, outBuf.length, dp.getAddress(), dp.getPort());
                ds.send(dpOut);
                ds.close();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getResponse(String command) {
        DateFormat df;
        String response;
        switch (command) {
            case "date":
                df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                response = "Todays date is: " + (df.format(new Date(System.currentTimeMillis())));
                break;

            case "time":
                df = DateFormat.getTimeInstance(DateFormat.SHORT);
                response = "The time is: " + (df.format(new Date(System.currentTimeMillis())));
                break;

            default:
                response = ("'" + command + "' is not a valid command.");
        }
        return response;
    }
}
