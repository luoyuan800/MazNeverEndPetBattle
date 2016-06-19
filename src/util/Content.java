package util;

/**
 * Created by luoyuan on 2016/6/19.
 */
public class Content {
    private StringBuilder msgBuilder;
    private static Content content = new Content();
    public static void addMessage(String msg){
        Content content = Content.content;
        if(content.msgBuilder == null){
            content.msgBuilder = new StringBuilder();
        }
        content.msgBuilder.append("<br>").append(msg);
    }
}
