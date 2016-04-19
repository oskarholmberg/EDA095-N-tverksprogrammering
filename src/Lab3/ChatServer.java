package Lab3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by erik on 13/04/16.
 */
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

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(connected){
                Socket clientSocket = serverSocket.accept();
                System.out.println("server loop...");

                if (clientSocket.isConnected()) {
                    System.out.println("Someone connected");
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
