package Lab3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerListenerThread extends Thread {
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public ServerListenerThread(Socket socket){
        this.socket=socket;
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
                String msg = scan.nextLine();
                System.out.println("You wrote: " + msg);
                msg+="\n";
                os.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
