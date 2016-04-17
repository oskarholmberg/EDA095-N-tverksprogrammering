package Lab2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by erik on 13/04/16.
 */
public class ChatServer {
    public static int ID;
    private ArrayList<ClientThread> clientList;
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

                if (clientSocket.isConnected())
                    System.out.println("Someone connected");

                if (!connected){
                    clientList.add(new ClientThread(clientSocket));
                }
            }

            try{
                serverSocket.close();

               // for (ClientThread c : clientList){
                //    c.close();
               // }

            } catch (Exception e){
                System.out.println(e);
            }


        } catch (Exception e){
            System.out.println(e);
        }
    }
}
