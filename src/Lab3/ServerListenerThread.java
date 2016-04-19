package Lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by erik on 17/04/16.
 */
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
                String temp = scan.nextLine();
                System.out.println("Du skrev: " + temp);
                os.write(temp.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
