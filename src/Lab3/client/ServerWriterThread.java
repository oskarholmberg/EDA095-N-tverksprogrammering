package Lab3.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerWriterThread extends Thread {
    private Socket socket;
    private OutputStream os;
    private String username;
    private Client client;

    public ServerWriterThread(Socket socket, String username, Client client){
        this.socket=socket;
        this.username = username;
        this.client=client;
        try {
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
                System.out.println("You wrote: " + msg);
                os.write(msg.getBytes());
                if(msg.contains("Q:")){
                    os.close();
                    socket.close();
                    client.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
