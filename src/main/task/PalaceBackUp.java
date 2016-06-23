package main.task;

import json.SimpleToken;
import rest.RestConnection;

import java.util.Map;

/**
 * Created by gluo on 6/23/2016.
 */
public class PalaceBackUp implements Runnable {
    static Map<Long, SimpleToken> levMap;
    RestConnection connection;
    public PalaceBackUp(RestConnection connection){
        this.connection = connection;
    }

    public void run(){
        int count = connection.getRowCount("NPCPlaceObject");
        if(count == 0 && levMap!=null && levMap.isEmpty()){
            for(SimpleToken token : levMap.values()){
                if(token.getValue("weight")==null) {
                    token.setValue("weight", 0);
                }
                token.removeValue("objectId");
                token.removeValue("createdAt");
                token.removeValue("updatedAt");
                connection.addObject("NPCPlaceObject", token.toJSONString());
            }
        }
    }
}
