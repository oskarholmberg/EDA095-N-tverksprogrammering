package Lab4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CrawlerThread extends Thread {
    private URLMonitor mon;

    public CrawlerThread(URLMonitor mon) {
        this.mon = mon;
    }

    public void run() {
        while (!mon.isFinished()) {
            try {
                Document doc = Jsoup.connect(mon.getURL()).get();
                Elements links = doc.select("a[href]");
                for (Element l : links) {
                    String link = l.attr("abs:href");
                    if (link.contains("mailto:"))
                        mon.addMail(link);
                    else
                        mon.addURL(link);
                }
            } catch (IOException e) {

            }
        }
    }
}
