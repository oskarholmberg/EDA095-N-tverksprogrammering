package Lab3.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    public static int ID;
    private ArrayList<ClientListenerThread> clientList;
    private int port;
    private boolean connected;
    private ExecutorService exec;
    private MessageMonitor mm;


    public ChatServer(int port){
        this.port=port;
        exec = Executors.newFixedThreadPool(10);
        mm = new MessageMonitor();

    }

    public void startServer(){
        connected = true;
        System.out.println("Trying to start server on port: " + port);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Success!");
            System.out.println("Server now running on port: " + port);

            while(connected){
                Socket clientSocket = serverSocket.accept();

                if (clientSocket.isConnected()) {
                    System.out.println("User @"+clientSocket.getInetAddress()+":"+ clientSocket.getPort() + " joined the channel.");
                    exec.submit(new ClientListenerThread(clientSocket, mm));
                    mm.newConnection(clientSocket.getInetAddress().toString()+":" + clientSocket.getPort(), clientSocket);
                }
            }


        } catch (Exception e){
            System.out.println("Server startup failed...");
            System.out.println("Check if port: " + port + " is available and try again.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new ChatServer(8080).startServer();
    }
}
