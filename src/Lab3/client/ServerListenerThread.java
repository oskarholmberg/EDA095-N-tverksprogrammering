package Lab3.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerListenerThread extends Thread{
    private InputStream is;
    private Socket socket;
    public ServerListenerThread(Socket socket){
        this.socket=socket;
        try {
            is=socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(!socket.isClosed()){
            try {
                System.out.println(readMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readMessage() throws IOException {

        int c = is.read();
        StringBuilder sb = new StringBuilder();
        while(c != -1 && c != 42){
            sb.append((char) c);
            c = is.read();
        }
        sb.append("\n");
        return sb.toString();
    }
}
