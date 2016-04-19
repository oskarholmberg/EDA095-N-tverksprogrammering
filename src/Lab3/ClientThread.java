package Lab3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by erik on 17/04/16.
 */
public class ClientThread extends Thread{
    private Socket clientSocket;
    private InputStream is;
    private OutputStream os;
    private int ID;
    private boolean connected;

    public ClientThread(Socket clientSocket) {
        this.clientSocket=clientSocket;
        ID = ++ChatServer.ID;

        try {
            is = clientSocket.getInputStream();
            os = clientSocket.getOutputStream();

        } catch(Exception e){

        }
    }

    public void run(){
        connected = true;

        while (connected) {

        }
    }
}
