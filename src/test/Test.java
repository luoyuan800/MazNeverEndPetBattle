package test;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

import json.JSON;
import json.SimpleToken;
import rest.RestConnection;

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
       /*
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
        RestConnection connection = new RestConnection();
        JSON json = connection.addObject("Test", "{\"name\":\"test1\",\"desc\":\"<img src='1'/>\"}");
        System.out.println(json.getContent());
    }
}
