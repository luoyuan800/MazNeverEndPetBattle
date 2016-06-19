package test;

import json.JSON;
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
        String content = "{\"results\":[{\"createdAt\":\"2015-11-17 22:49:04\",\"lev\":1000,\"name\":\"XXXX\",\"objectId\":\"61ad99b239\",\"updatedAt\":\"2015-11-17 22:49:04\"},{\"createdAt\":\"2015-11-17 21:54:16\",\"lev\":2,\"name\":\"234\",\"objectId\":\"yUoqKKKP\",\"updatedAt\":\"2015-11-17 21:54:54\",\"value\":34},{\"createdAt\":\"2016-06-18 00:19:39\",\"lev\":1112,\"name\":\"nnnn\",\"objectId\":\"c0012b6123\",\"updatedAt\":\"2016-06-18 00:19:39\"}]}";
        JSON json = new JSON(content);
        json.parse();
        System.out.print(json.getTokens().size());
    }
}
