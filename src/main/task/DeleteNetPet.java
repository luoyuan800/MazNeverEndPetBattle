package main.task;

import json.JSON;
import json.SimpleToken;
import rest.RestConnection;
import util.Content;

/**
 * Created by gluo on 6/22/2016.
 */
public class DeleteNetPet implements Runnable {
    private RestConnection connection;
    public DeleteNetPet(RestConnection connection){
        this.connection = connection;
    }
    @Override
    public void run() {
        try {
            Content.log("执行竞技场宠物个数限制任务");
            int count = connection.getRowCount("NetPet");
            if (count > 150) {
                Content.log("当前竞技场宠物个数为" + count);
                JSON netPet = connection.queryObjects("NetPet", count - 100);
                netPet.parse();
                netPet.getTokens().stream().filter(token -> token.<Long>getValue("point") > 10000).forEach(token -> {
                    Content.log("删除" + token.getValue("name") + "|" + token.getValue("keeper"));
                    connection.deleteObject("NetPet", token.getValue("objectId"));
                });
            }
            Content.log("宠物竞技场清理完成");
        }catch (Exception e){
            Content.error("清理竞技场错误",e);
        }
    }
}
