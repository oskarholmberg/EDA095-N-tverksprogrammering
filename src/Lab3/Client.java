package Lab3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String server, username;
    private int port;

    public Client(){
        port=8080;
        server="localhost";
        username="erk";
    }

    public void startClient(){
        try{
            socket = new Socket(server, port);
            System.out.println("Client socket started.");
        } catch(Exception e){
            System.out.println(e);
        }

        new ServerListenerThread(socket, username).start();
    }

    public static void main(String[] args){
        Client client = new Client();

        client.startClient();
    }
}
