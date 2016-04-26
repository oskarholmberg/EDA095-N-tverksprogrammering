package Lab3.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerWriterThread extends Thread {
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private String username;

    public ServerWriterThread(Socket socket, String username){
        this.socket=socket;
        this.username = username;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        Scanner scan = new Scanner(System.in);
        while(!socket.isClosed()){
            try {
                String info = username + ": ";
                String msg = scan.nextLine();
                msg=info + msg + "*";
                os.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
