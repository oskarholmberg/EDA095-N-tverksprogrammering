package Lab1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFDownloader {
    public static void main(String[] args) {
        download("http://cs229.stanford.edu/materials.html");
    }

    private static void download(String url) {
        String content = "";
        URLConnection connection;
        ExecutorService exec = Executors.newFixedThreadPool(10);

        //----- Connect to website and scan the content ----- //

        try {
            connection = new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //----- Regular expressions ----- //

        //First find all lines with links.
        Pattern htmltag = Pattern.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
        //Then find which of these have hrefs.
        Pattern link = Pattern.compile("href=\\\"[^>]*\\\">");
        //Lastly check which of these hrefs refer to .pdf sources.
        Pattern pdf = Pattern.compile("(.*)\\.pdf");
        Matcher tagMatch = htmltag.matcher(content);

        int i = 1;
        //For each html tag match...
        while (tagMatch.find()) {
            //... find links with href...
            Matcher m = link.matcher(tagMatch.group());
            //... for each line with href...
            while (m.find()) {
                Matcher pdfM = pdf.matcher(m.group());
                //... find the ones referring to a .pdf source...
                if (pdfM.find()) {
                    //... open a stream and download the hits.
                    String pdfUrl = pdfM.group(0).replaceAll("href=\"", "");
                    exec.submit( new DownloadThread(url, pdfUrl, i));
                    i++;
                }
            }
        }
        exec.shutdown();
        if (i == 1) {
            System.out.println(i + " pdf document found.");
        } else {
            System.out.println(i + " pdf documents found.");
        }
    }
}
