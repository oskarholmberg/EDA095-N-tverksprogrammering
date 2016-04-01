package Lab1;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oskar on 4/1/16.
 */
public class PDFDownloader {
    public static void main(String[] args){
        download("http://cs.lth.se/eda095/foerelaesningar/?no_cache=1");
    }

    private static void download(String url){
        String content = "";
        URLConnection connection;
        try {
            connection =  new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

        Pattern htmltag = Pattern.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
        Pattern link = Pattern.compile("href=\\\"[^>]*\\\">");
        Matcher tagMatch = htmltag.matcher(content);

        while(tagMatch.find()){
            Matcher m = link.matcher(tagMatch.group());
            while (m.find()){
                System.out.println(m.group(0));
            }
        }
    }
}
