package Lab3.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MessageMonitor {
    private ArrayList<Message> msgList;
    private HashMap<String, Socket> clientList;
    private ExecutorService exec;

    public MessageMonitor() {
        msgList = new ArrayList<>();
        clientList = new HashMap<>();
        exec = Executors.newFixedThreadPool(10);
        new MonitorThread(this).start();
    }

    public void newConnection(String inetAddress, Socket clientSocket) {
        clientList.put(inetAddress, clientSocket);
    }

    public synchronized void newMsg(Message msg) {
        msgList.add(msg);
        notifyAll();
    }

    public synchronized void fetchMsg() throws InterruptedException {
        if (msgList.isEmpty()) {
            wait();
        } else {
            Message msg = msgList.remove(0);
            if (msg.getType().equals("E")) {
                exec.submit(new EchoThread(clientList.get(msg.getInetAddress()), msg));
            } else if (msg.getType().equals("M")){
                for(String s : clientList.keySet()){
                    System.out.println("Sending to: " + s);
                    exec.submit(new EchoThread(clientList.get(s), msg));
                }
            } else if (msg.getType().equals("Q")){
                try {
                    clientList.get(msg.getInetAddress()).close();
                    clientList.remove(msg.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                exec.submit(new EchoThread(clientList.get(msg.getInetAddress()), msg));
            }
            if (msg.getType().equals("M")) {
                for (String s : clientList.keySet()) {
                    exec.submit(new EchoThread(clientList.get(s), msg));
                }
            }
        }
    }
}
