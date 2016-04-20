package Lab3.server;

/**
 * Created by erik on 19/04/16.
 */
public class Message {
    private String s;
    private String username;
    private String type;
    private String content;
    private String inetAddress;

    public Message(String s, String inetAddress){
        this.s=s;
        this.inetAddress=inetAddress;
    }

    public boolean splitString(){
        String[] split = s.split(":");
        if (split.length<3){
            return false;
        }
        username=split[0].trim();
        type=split[1].trim();
        content=split[2].trim();
        return true;
    }
    public String getInetAddress(){ return inetAddress;}
    public String getType(){
        return type;
    }

    public String getMessage(){
        return username+ ":" + content;
    }

}
