package Lab3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientListenerThread extends Thread{
    private Socket clientSocket;
    private InputStream is;
    private OutputStream os;
    private int ID;

    public ClientListenerThread(Socket clientSocket) {
        this.clientSocket=clientSocket;
        ID = ++ChatServer.ID;

        try {
            is = clientSocket.getInputStream();
            os = clientSocket.getOutputStream();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){

        while (true) {
            try {
                int c = is.read();
                String msg;
                if(c != -1){
                    System.out.print((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
