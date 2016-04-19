package Lab3;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.*;
import java.net.Socket;

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
                System.out.println(is.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
