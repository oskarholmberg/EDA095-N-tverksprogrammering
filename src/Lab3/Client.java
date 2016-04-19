package Lab3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by erik on 17/04/16.
 */
public class Client {
    private InputStream is;
    private OutputStream os;
    private Socket socket;
    private String server, username;
    private int port;

    public Client(){
        port=8080;
        server="localhost";
        username="anon";
    }

    public void start(){
        try{
            socket = new Socket(server, port);
            System.out.println("Client socket started.");
        } catch(Exception e){
            System.out.println(e);
        }

        try{
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (Exception e){
            System.out.println(e);
        }

        new ServerListenerThread().start();
    }

    public static void main(String[] args){
        Client client = new Client();

        client.start();
    }
}
