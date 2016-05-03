package Lab4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ghostcrawler {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        URLMonitor mon = new URLMonitor("http://cs.lth.se/pierre_nugues/", 20);
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            exec.submit(new CrawlerThread(mon));
        }
        while(!mon.isFinished()){
        }
        System.out.println("It took: " + (System.currentTimeMillis()-t1) + " ms");
        System.exit(0);
    }
}
