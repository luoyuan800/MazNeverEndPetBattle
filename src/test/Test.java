package test;

import json.JSON;
import main.NetPetBattle;
import rest.RestConnection;
import util.Random;
import util.StringUtils;

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
       /* RestConnection connection = new RestConnection();
////        JSON json = connection.addObject("PetBattleMsg", "{\"twoP\":\"普通的中巨型飞蛾♂<br>(水)\",\"oneP\":\"普通的中大蚯蚓♀(火)\"}");
        NetPetBattle battle = new NetPetBattle(connection);
        battle.run();
        System.out.println("done");*/
//        Random random = new Random();
//        for(int i =0; i <100 ;i++){
//            Long atk_rise = random.nextLong(999);
//            Long def_rise = random.nextLong(999);
//            Long hp_rise = random.nextLong(999);
//            Long uHp = (long)random.nextInt();
//            Long def = (long)random.nextInt();
//            Long atk = (long)random.nextInt();
//            if(uHp<0){
//                uHp*=-1;
//            }
//            if(atk<0){
//                atk*=-1;
//            }
//            if(def<0){
//                def*=-1;
//            }
//            System.out.println(String.format("hp:%s|atk:%s|def:%s, hp_rise:%s|atk_rise:%s|def_rise:%s", uHp, atk, def, hp_rise, atk_rise, def_rise));
//            atk_rise = StringUtils.reduceToSpecialDigit(atk_rise, 1) * atk_rise.toString().length();
//            def_rise = StringUtils.reduceToSpecialDigit(def_rise, 1) * def_rise.toString().length();
//            hp_rise = StringUtils.reduceToSpecialDigit(hp_rise, 1) * hp_rise.toString().length();
//            uHp = StringUtils.reduceToSpecialDigit(uHp, 2) * uHp.toString().length() * hp_rise;
//            atk = StringUtils.reduceToSpecialDigit(atk, 2) * atk.toString().length() * atk_rise;
//            def = StringUtils.reduceToSpecialDigit(def, 2) * def.toString().length() * def_rise;
//            System.out.println(String.format("hp:%s|atk:%s|def:%s, hp_rise:%s|atk_rise:%s|def_rise:%s", uHp, atk, def, hp_rise, atk_rise, def_rise));
//            System.out.println("------");
//        }
        String dd = "\"atk\":10,\"atk_rise\":10,\"battleIds\":[\"f3cb857a0f\",\"3344cb6ac1\"],\"color\":\"#556B2F\",\"createdAt\":\"2016-06-19 21:39:55\",\"deathCount\":0,\"def\":14,\"def_rise\":14,\"dodge\":15,\"eggRate\":300,\"element\":\"火\",\"fName\":\"未知\",\"hit\":0,\"hp_rise\":20,\"id\":\"368c0fc6-46dc-4d44-b8fe-8c53a147b92d\",\"image\":2.130837646e+09,\"index\":2,\"intimacy\":0,\"keeperId\":\"e8affcb1-fb46-48e3-9df7-b6d1779eacc3\",\"lev\":1,\"lost\":0,\"mName\":\"未知\",\"metare\":1000,\"name\":\"普通的中大蚯蚓\",\"objectId\":\"a83f9a3929\",\"onUsed\":false,\"owner\":\"勇者\",\"ownerId\":\"e8afecb1-fb46-48e3-9df7-b6d1779eacc3\",\"parry\":0,\"point\":1,\"race\":5,\"ranking\":2,\"sex\":1,\"skill\":\"\",\"store\":0,\"tag\":\"\",\"type\":\"大蚯蚓\",\"uHp\":20,\"updatedAt\":\"2016-06-21 14:41:52\",\"victor\":0";
        String[] split = dd.split("(?!=\\[)(?<=.*),(?=.*\")(?!=\\])");
    }
}
