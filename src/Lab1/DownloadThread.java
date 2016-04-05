package Lab1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by erik on 05/04/16.
 */
public class DownloadThread extends Thread {
    private String url;
    private String relPath;
    private int index;

    public DownloadThread(String url, String relPath, int index){
        this.url=url;
        this.relPath=relPath;
        this.index=index;
    }

    @Override
    public void run() {
        InputStream in;
        try {
            in = new URL(new URL(url), relPath).openStream();
            //Files.copy(in, Paths.get("download" + index + ".pdf"), StandardCopyOption.REPLACE_EXISTING);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(relPath);
    }
}
