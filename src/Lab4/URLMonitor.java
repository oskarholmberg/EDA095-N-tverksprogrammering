package Lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;

public class URLMonitor {
    private ArrayList<String> remaining;
    private ArrayList<String> traversed;
    private ArrayList<String> mails;
    private ArrayList<String> links;
    private boolean printed=false;
    private final int MAX_URL;

    public URLMonitor(String startUrl, int MAX_URL){
        this.MAX_URL=MAX_URL;
        remaining = new ArrayList<>();
        traversed = new ArrayList<>();
        mails = new ArrayList<>();
        links = new ArrayList<>();
        remaining.add(startUrl);
    }

    public synchronized String getURL(){
        if(remaining.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        traversed.add(remaining.get(0));
        return remaining.remove(0);
    }

    public synchronized void addURL(String url){
        if(!traversed.contains(url)){
            remaining.add(url);
            notifyAll();
        }
        if (!links.contains(url)){
            links.add(url);
        }
    }

    public synchronized void addMail(String url){
        if(!mails.contains(url)){
            mails.add(url);
        }
    }

    public synchronized boolean isFinished(){
        if((links.size()+mails.size())<MAX_URL)
            return false;

        if (!printed) {
            printed=true;
            try {
                PrintWriter pw = new PrintWriter(new File("GhostcrawlerOutput.txt"));
                pw.write("Links:\n");
                for(String s : links)
                    pw.write(s+"\n");
                pw.write("Emails:\n");
                for (String s : mails)
                    pw.write(s+"\n");
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Crawling finished! \nFound a total of: " + (links.size()+mails.size()) + " links");
        }
        return true;
    }
}
