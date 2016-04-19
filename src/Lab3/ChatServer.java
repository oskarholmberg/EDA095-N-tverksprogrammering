package Lab3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    public static int ID;
    private ArrayList<ClientListenerThread> clientList;
    private int port;
    private boolean connected;


    public ChatServer(int port){
        this.port=port;
    }

    public void startServer(){
        connected = true;
        System.out.println("Starting server on port: " + port);
        System.out.println("Server running...");

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(connected){
                Socket clientSocket = serverSocket.accept();

                if (clientSocket.isConnected()) {
                    System.out.println("User @"+clientSocket.getInetAddress()+":"+ clientSocket.getPort() + " joined the channel.");
                    new ClientListenerThread(clientSocket).start();
                }
            }

            try{
                serverSocket.close();

               // for (ClientListenerThread c : clientList){
                //    c.close();
               // }

            } catch (Exception e){
                e.printStackTrace();
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new ChatServer(8080).startServer();
    }
}
