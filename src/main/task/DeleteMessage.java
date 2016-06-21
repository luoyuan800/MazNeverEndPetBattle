package main.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import json.JSON;
import json.SimpleToken;
import rest.RestConnection;

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
        System.out.println("执行消息清除任务。" + new Date());
        int count = connection.getRowCount("PetBattleMsg");
        if(count > 100){
            System.out.println("消息列表数量为" + count);
            JSON json = connection.queryObjects("PetBattleMsg");
            json.parse();
            for(SimpleToken token : json.getTokens()){
                connection.deleteObject("PetBattleMsg", token.getValue("objectId"));
            }
            System.out.println("删除消息完成");
        }
        System.out.println("消息清除任务结束。" + new Date());
    }

    public static void main(String... args){
        RestConnection connection = new RestConnection();
        DeleteMessage deleteMessage = new DeleteMessage(connection);
        deleteMessage.run();
    }
}
