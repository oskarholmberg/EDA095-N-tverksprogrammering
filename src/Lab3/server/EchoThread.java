package Lab3.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by erik on 20/04/16.
 */
public class EchoThread extends Thread {
    private Socket clientSocket;
    private Message msg;
    public EchoThread(Socket clientSocket, Message msg){
        this.clientSocket=clientSocket;
        this.msg=msg;
    }

    public void run(){
        try {
            OutputStream os = clientSocket.getOutputStream();
            os.write((msg.getMessage()+"*").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
