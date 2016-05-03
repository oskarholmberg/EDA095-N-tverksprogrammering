package Lab5.Server;

import java.text.DateFormat;
import java.util.Date;

public class TimeServer {

    public TimeServer() {

    }

    public synchronized String getResponse(String command) {
        DateFormat df;
        String response;
        switch (command) {
            case "date":
                df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                response = (df.format(new Date(System.currentTimeMillis())));
                break;

            case "time":
                df = DateFormat.getTimeInstance(DateFormat.SHORT);
                response = (df.format(new Date(System.currentTimeMillis())));
                break;

            default:
                response = ("'" + command + "' is not a valid command.");
        }
        return response;
    }
}
