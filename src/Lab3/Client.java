package Lab3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by erik on 17/04/16.
 */
public class Client {
    private Socket socket;
    private String server, username;
    private int port;

    public Client(){
        port=8080;
        server="localhost";
        username="anon";
    }

    public void startClient(){
        try{
            socket = new Socket(server, port);
            System.out.println("Client socket started.");
        } catch(Exception e){
            System.out.println(e);
        }

        new ServerListenerThread(socket).start();
    }

    public static void main(String[] args){
        Client client = new Client();

        client.startClient();
    }
}
