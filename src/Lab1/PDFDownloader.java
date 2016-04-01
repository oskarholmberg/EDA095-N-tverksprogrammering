package Lab1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oskar on 4/1/16.
 */
public class PDFDownloader {
    public static void main(String[] args){
        download("http://cs.lth.se/eda095/tentamen/");
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
        Pattern pdf = Pattern.compile("(.*)\\.pdf");
        Matcher tagMatch = htmltag.matcher(content);

        int i = 1;
        while(tagMatch.find()){
            Matcher m = link.matcher(tagMatch.group());
            while (m.find()){
                Matcher pdfM = pdf.matcher(m.group());

                if (pdfM.find()){
                    String pdfUrl = pdfM.group(0).replaceAll("href=\"", "");
                    InputStream in;
                    try {
                        URL dl = new URL(pdfUrl);
                        in = dl.openStream();
                        System.out.println(pdfUrl);
                        //Files.copy(in, Paths.get("download" + i + ".pdf"), StandardCopyOption.REPLACE_EXISTING);
                        i++;
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Found " + i + " pdf documents.");
    }
}
