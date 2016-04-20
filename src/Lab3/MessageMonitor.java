package Lab3;

import java.util.ArrayList;

/**
 * Created by erik on 19/04/16.
 */
public class MessageMonitor {
    private ArrayList<Message> msgList;
    public MessageMonitor(){
        msgList = new ArrayList<>();
    }

    public synchronized void newMsg(Message msg){
        msgList.add(msg);
        notifyAll();
    }

    public synchronized void fetchMsg() throws InterruptedException {
        if(msgList.isEmpty())
            wait();
        //Add code for fetching message.
    }
}
