package util;

/**
 * Created by luoyuan on 2016/6/19.
 */
public class Content {
    public static final long skill_cost = 1;
    private final static String enter = "<br>";
    private StringBuilder msgBuilder;
    private static Content content = new Content();
    public static void addMessage(String msg){
        Content content = Content.content;
        if(content.msgBuilder == null){
            content.msgBuilder = new StringBuilder();
        }
        content.msgBuilder.append(enter).append(msg);
    }
    public static String getMessage(boolean clear){
        try {
            Content content = Content.content;
            if (content.msgBuilder != null) {
                return content.msgBuilder.toString();
            }else{
                return null;
            }
        }finally {
            if(clear){
                content.msgBuilder = null;
            }
        }
    }
}
