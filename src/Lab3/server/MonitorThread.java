package Lab3.server;

/**
 * Created by erik on 20/04/16.
 */
public class MonitorThread extends Thread{
    private MessageMonitor mm;
    public MonitorThread(MessageMonitor mm){
        this.mm=mm;

    }

    public void run(){
        while(true){
            try {
                mm.fetchMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
