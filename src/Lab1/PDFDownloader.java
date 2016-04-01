package Lab1;

/**
 * Created by oskar on 4/1/16.
 */
public class PDFDownloader {
    public static void main(String[] args){
        download("http://cs.lth.se/eda095/laborationer/laboration-1/");
    }

    private static void download(string url){
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        System.out.println(content);
    }
}
