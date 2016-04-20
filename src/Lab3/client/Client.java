package Lab3.client;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    private String server, username;
    private int port;
    private ExecutorService exec;

    public Client(){
        port=8080;
        server="localhost";
        username="erk";
        exec = Executors.newFixedThreadPool(2);
    }

    public void startClient(){
        try{
            socket = new Socket(server, port);
            System.out.println("client socket started.");
        } catch(Exception e){
            System.out.println(e);
        }
        exec.submit(new ServerWriterThread(socket, username));
        exec.submit(new ServerListenerThread(socket));
    }

    public static void main(String[] args){
        Client client = new Client();

        client.startClient();
    }
}
