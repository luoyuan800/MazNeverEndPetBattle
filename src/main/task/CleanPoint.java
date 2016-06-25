package main.task;

import json.JSON;
import json.SimpleToken;
import rest.RestConnection;
import util.Content;

/**
 * Created by luoyuan on 2016/6/25.
 */
public class CleanPoint implements Runnable {
    RestConnection connection;
    public CleanPoint(RestConnection connection){
        this.connection = connection;
    }
    @Override
    public void run() {
        JSON json = connection.queryObjects("NetPet");
        json.parse();
        Content.log("开始重置宠物排行");
        Content.log("宠物数量为：" + json.getTokens().size());
        for(SimpleToken token : json.getTokens()){
            connection.updateObject("NetPet", token.<String>getValue("objectId"), "{point:0,ranking:0}");
        }
        Content.log("重置宠物排行结束");
    }
}
