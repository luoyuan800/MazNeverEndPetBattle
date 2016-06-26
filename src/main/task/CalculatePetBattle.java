package main.task;

import java.text.DateFormat;
import java.text.Format;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

import json.JSON;
import json.SimpleToken;
import rest.RestConnection;
import util.Content;
import util.Race;
import util.StringUtils;

/**
 * Created by luoyuan on 2016/6/26.
 */
public class CalculatePetBattle implements Runnable {
    RestConnection connection;

    public CalculatePetBattle(RestConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {

            StringBuffer sb = new StringBuffer("宠物竞技周报." + DateFormat.getInstance().format(new Date()));
            JSON json = connection.queryObjects("NetPet");
            json.parse();
            long total = 0;
            Map<Long, Long> raceRich = new HashMap<>(6);
            Map<Long, Long> racePoint = new HashMap<>(6);
            Map<Long, Long> raceCount = new HashMap<>(6);
            long sex = 0;
            for (SimpleToken token : json.getTokens()) {
                total++;
                if(StringUtils.toInt(token.getValue("sex")) == 0){
                    sex ++;
                }
                long race = token.getValue("race");
                long point = token.getValue("point");
                long mate = StringUtils.toLong(token.getValue("metare"));
                Long race_count = raceCount.get(race);
                if (race_count == null) {
                    raceCount.put(race, 1L);
                } else {
                    raceCount.put(race, race_count + 1);
                }
                Long race_point = racePoint.get(race);
                if (race_point == null) {
                    racePoint.put(race, point);
                } else {
                    racePoint.put(race, race_point + point);
                }
                Long race_rich = raceRich.get(race);
                if (race_rich == null) {
                    raceRich.put(race, mate);
                } else {
                    raceRich.put(race, race_rich + mate);
                }
            }
            Long maxCountRace = null;
            long maxValue = 0;
            for (Map.Entry<Long, Long> entry : raceCount.entrySet()) {
                if (entry.getValue() > maxValue) {
                    maxCountRace = entry.getKey();
                    maxValue = entry.getValue();
                }
            }
            if (maxCountRace != null) {
                sb.append("\n").append("参战数量最多的种族是：").append(Race.getByIndex(maxCountRace.intValue()).getName()).append("。占比").append((maxValue * 100) / total).append("%");
            }
            Long maxRichRace = null;
            maxValue = 0;
            for (Map.Entry<Long, Long> entry : raceRich.entrySet()) {
                if (entry.getValue() > maxValue) {
                    maxRichRace = entry.getKey();
                    maxValue = entry.getValue();
                }
            }
            if (maxRichRace != null) {
                sb.append("\n").append("最有钱的种族是：").append(Race.getByIndex(maxRichRace.intValue()).getName());
            }
            Long maxPointRace = null;
            maxValue = 0;
            for (Map.Entry<Long, Long> entry : racePoint.entrySet()) {
                if (entry.getValue() > maxValue) {
                    maxPointRace = entry.getKey();
                    maxValue = entry.getValue();
                }
            }
            if (maxPointRace != null) {
                sb.append("\n").append("综合战力最高的种族是：").append(Race.getByIndex(maxPointRace.intValue()).getName());
            }
            sb.append("\n").append("性别：♂ ").append(sex*100/total).append("%，♀ ").append((total-sex)*100/total).append("%");
            Content.log(sb.toString());
            //connection.addObject("NetPetReport", "{report:\"" + sb.toString() + "\"}");
        }catch (Exception e){
            e.printStackTrace();
            Content.error("计算周报错误",e);
        }

    }

    public static void main(String...args){
        RestConnection connection = new RestConnection();
        CalculatePetBattle calculatePetBattle = new CalculatePetBattle(connection);
        calculatePetBattle.run();
    }
}
