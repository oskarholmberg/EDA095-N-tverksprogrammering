package Lab4;

import java.util.ArrayList;

/**
 * Created by erik on 26/04/16.
 */
public class Ghostcrawler {
    public static void main(String[] args){
        URLMonitor mon = new URLMonitor("http://cs.lth.se/pierre_nugues/", 2000);
        new CrawlerThread(mon).start();
    }
}
