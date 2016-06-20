package test;

import json.JSON;
import main.NetPetBattle;
import rest.RestConnection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by luoyuan on 2016/6/17.
 */
public class Test {
    public static void main(String...args) throws IOException {
//        String body = "{\"lev\":1112,\"name\":\"nnnn\"}";
//        URL url = new URL("https://api.bmob.cn/1/classes/Test/c0012b6123");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestProperty("X-Bmob-Application-Id", "48");
//        conn.setRequestProperty("X-Bmob-REST-API-Key", "f");
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setRequestMethod("GET");
////        conn.setDoOutput(true);
////        conn.getOutputStream().write(body.getBytes());
//        conn.connect();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line = reader.readLine();
//        while(line!=null){
//            System.out.println(line);
//            line = reader.readLine();
//        }
//        reader.close();
        RestConnection connection = new RestConnection();
//        JSON json = connection.addObject("PetBattleMsg", "{\"twoP\":\"普通的中巨型飞蛾♂<br>(水)\",\"oneP\":\"普通的中大蚯蚓♀(火)\"}");
        NetPetBattle battle = new NetPetBattle(connection);
        battle.run();
        System.out.println("done");
    }
}
