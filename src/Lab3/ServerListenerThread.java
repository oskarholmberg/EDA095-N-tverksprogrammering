package Lab3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerListenerThread extends Thread {
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private String username;

    public ServerListenerThread(Socket socket, String username){
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
        while(true){
            try {
                String info = username + ":\n";
                String msg = scan.nextLine();
                System.out.println("You wrote: " + msg);
                msg=info + msg + "\n";
                os.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
