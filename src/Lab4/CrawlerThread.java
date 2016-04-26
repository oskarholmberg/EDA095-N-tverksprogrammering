package Lab4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by erik on 26/04/16.
 */
public class CrawlerThread extends Thread{
    private URLMonitor mon;

    public CrawlerThread(URLMonitor mon){
        this.mon=mon;
    }

    public void run(){
        int i = 0;
        while(!mon.isFinished()){
            try {
                i++;
                System.out.println("Level:" + i);
                Document doc = Jsoup.connect(mon.getURL()).get();
                Elements mailtos = doc.select("mailto");
                for (Element mailto:mailtos){
                    mon.addMail(mailto.attr("abs:href"));
                }
                Elements links = doc.select("a[href]");
                for (Element link:links){
                    mon.addURL(link.attr("abs:href"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
