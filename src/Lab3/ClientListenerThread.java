package Lab3;

import java.io.*;
import java.net.Socket;

public class ClientListenerThread extends Thread{
    private Socket clientSocket;
    private InputStream is;
    private MessageMonitor mm;
    private int ID;

    public ClientListenerThread(Socket clientSocket, MessageMonitor mm) {
        this.clientSocket=clientSocket;
        this.mm=mm;
        ID = ++ChatServer.ID;

        try {
            is = clientSocket.getInputStream();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        while (true) {
            try {
                StringBuilder sb = new StringBuilder();
                int c = is.read();
                while(c != -1 && c!=42){
                    sb.append((char) c);
                    c = is.read();
                }
                sb.append("\n");
                Message msg = new Message(sb.toString());
                if (msg.splitString()){
                    mm.newMsg(msg);
                    System.out.println(msg.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
