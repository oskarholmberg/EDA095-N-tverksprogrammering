package Lab3;

/**
 * Created by erik on 19/04/16.
 */
public class Message {
    private String s;
    private String username;
    private String type;
    private String content;

    public Message(String s){
        this.s=s;
    }

    public boolean splitString(){
        String[] split = s.split(":");
        if (split.length<3){
            return false;
        }
        username=split[0];
        type=split[1];
        content=split[2];
        return true;
    }
    public String getType(){
        return type;
    }

    public String getMessage(){
        return username+ ":" + content;
    }

}
