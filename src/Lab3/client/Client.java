package Lab3.client;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    private String server, username;
    private int port;
    private ServerWriterThread swt;
    private ServerListenerThread slt;

    public Client(){
        port=8080;
        server="localhost";
        username="erk";
    }

    public void startClient(){
        try{
            socket = new Socket(server, port);
            System.out.println("client socket started.");
        } catch(Exception e){
            System.out.println(e);
        }
        swt = new ServerWriterThread(socket, username, this);
        slt = new ServerListenerThread(socket);
        swt.start();
        slt.start();
    }

    public void disconnect(){
        slt.interrupt();
        swt.interrupt();
        try {
            slt.join();
            swt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Client().startClient();
    }
}
