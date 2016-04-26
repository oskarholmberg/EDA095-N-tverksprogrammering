package Lab3.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String server, username;
    private int port;
    private ServerWriterThread swt;
    private ServerListenerThread slt;

    public Client(){
        port=8080;
        server="31.208.65.146";
        username="Erik";
    }

    public void startClient(){
        try{
            socket = new Socket(server, port);
            System.out.println("Connected to server @"+ server + ":" + port);
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
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Client().startClient();
    }
}
