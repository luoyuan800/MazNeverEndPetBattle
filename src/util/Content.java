package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

/**
 * Created by luoyuan on 2016/6/19.
 */
public class Content {
    private static final Logger logger = Logger.getLogger("MazeNeverEndServer");
    static{
        try {
            FileHandler handler = new FileHandler("D:\\maze\\logs\\log.maze", 40240000, 30, true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final long skill_cost = 5000;
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

    public static void log(String log){
        logger.log(Level.INFO, log);
    }
    public static void warn(String log, Throwable throwable){
        if(throwable!=null){
            logger.log(Level.WARNING, log, throwable);
        }else{
            logger.log(Level.WARNING, log);
        }
    }
    public static void error(String log, Throwable throwable){
        if(throwable!=null){
            logger.log(Level.SEVERE, log, throwable);
        }else{
            logger.log(Level.SEVERE, log);
        }
    }
}
