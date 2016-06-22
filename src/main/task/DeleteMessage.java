package main.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import json.JSON;
import json.SimpleToken;
import rest.RestConnection;
import util.Content;
import util.StringUtils;

/**
 * Created by luoyuan on 2016/6/21.
 */
public class DeleteMessage implements Runnable {
    private RestConnection connection;
    public DeleteMessage(RestConnection connection){
        this.connection = connection;
    }
    @Override
    public void run() {
        try {
            Content.log("执行消息清除任务。" + new Date());
            int count = connection.getRowCount("PetBattleMsg");
            if (count > 1500) {
                Content.log("消息列表数量为" + count);
                JSON json = connection.queryObjects("PetBattleMsg", 500);
                json.parse();
                for (SimpleToken token : json.getTokens()) {
                    connection.deleteObject("PetBattleMsg", token.getValue("objectId"));
                }
                Content.log("删除消息完成" + json.getTokens().size());
            }
            Content.log("消息清除任务结束。" + new Date());
        }catch (Exception e){
            Content.error("删除宠物对战信息出错",e);
        }
    }

    public static void main(String... args){
        RestConnection connection = new RestConnection();
        DeleteMessage deleteMessage = new DeleteMessage(connection);
        deleteMessage.run();
    }
}
