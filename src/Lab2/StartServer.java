package Lab2;

/**
 * Created by erik on 17/04/16.
 */
public class StartServer {
    public static void main(String[] args){
            new ChatServer(8080).startServer();
    }
}
