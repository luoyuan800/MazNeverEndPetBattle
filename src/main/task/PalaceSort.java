package main.task;

import json.JSON;
import json.SimpleToken;
import rest.RestConnection;
import util.Content;
import util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gluo on 6/22/2016.
 */
public class PalaceSort implements Runnable {
    int version = 281;
    RestConnection connection;
    public PalaceSort(RestConnection connection){
        this.connection = connection;
    }
    @Override
    public void run() {
        try {
            Content.log("开始运行殿堂排名计算");
            int count = connection.getRowCount("uploader");
            JSON uploader = connection.queryObjects("uploader", count);
            uploader.parse();
            Content.log("清空uploader表");
            for (SimpleToken token : uploader.getTokens()) {
                connection.deleteObject("uploader", token.getValue("objectId"));
            }
            JSON palace = connection.queryObjects("NPCPlaceObject", "-lev", 1000);
            palace.parse();
            Map<String, SimpleToken> idMap = new HashMap<>();
            Map<Long, SimpleToken> levMap = new HashMap<>();
            List<SimpleToken> addList = new ArrayList<>();
            for (SimpleToken token : uploader.getTokens()) {
                String uuid = token.<String>getValue("uuid");
                int version = StringUtils.toInt(token.getValue("version"));
                if (version >= this.version) {
                    SimpleToken exist = idMap.get(uuid);
                    if (exist != null) {
                        long lev = StringUtils.toLong(exist.getValue("lev"));
                        levMap.remove(lev);
                        addList.remove(exist);
                    }
                    idMap.put(uuid, token);
                    levMap.put(StringUtils.toLong(token.getValue("lev")), token);
                    addList.add(token);
                }
            }
            Content.log("新晋殿堂候选人数" + addList.size());
            List<SimpleToken> removeList = new ArrayList<>();
            List<SimpleToken> stayList = new ArrayList<>();
            List<SimpleToken> updateList = new ArrayList<>();
            Content.log("处理已有殿堂数据更新");
            for (SimpleToken token : palace.getTokens()) {
                String id = token.getValue("uuid");
                long lev = StringUtils.toLong(token.getValue("lev"));
                SimpleToken idToken = idMap.get(id);
                if (idToken != null) {
                    //update
                    idToken.setValue("objectId", token.getValue("objectId"));
                    updateList.add(idToken);
                    addList.remove(idToken);
                } else {
                    SimpleToken levToken = levMap.get(lev);
                    if (levToken != null) {
                        //remove
                        removeList.add(token);
                    } else {
                        //Stay
                        int version = StringUtils.toInt(token.getValue("version"));
                        if (version >= this.version) {
                            stayList.add(token);
                            levMap.put(lev, token);
                        } else {
                            removeList.add(token);
                        }
                    }
                }
            }
            calculateWeight(levMap, stayList);
            calculatePalaceAward(levMap);
            Content.log("备份殿堂数据");
            PalaceBackUp.levMap = levMap;
            if (!addList.isEmpty()) {
                Content.log("添加殿堂新晋人物，数量：" + addList.size());
                for (SimpleToken token : addList) {
                    token.setValue("weight", 0);
                    token.removeValue("objectId");
                    token.removeValue("createdAt");
                    token.removeValue("updatedAt");
                    connection.addObject("NPCPlaceObject", token.toJSONString());
                }
            }
            if (!updateList.isEmpty()) {
                Content.log("更新殿堂旧数据，更新人物数：" + updateList.size());
                for (SimpleToken token : updateList) {
                    token.setValue("weight", 0);
                    String objectId = token.getValue("objectId");
                    token.removeValue("objectId");
                    token.removeValue("createdAt");
                    token.removeValue("updatedAt");
                    connection.updateObject("NPCPlaceObject", objectId, token.toJSONString());
                }
            }
            if (!removeList.isEmpty()) {
                Content.log("删除殿堂旧数据（同楼层被替换），删除人物数：" + removeList.size());
                for (SimpleToken token : removeList) {
                    connection.deleteObject("NPCPlaceObject", token.getValue("objectId"));
                }
            }
            if (!stayList.isEmpty()) {
                Content.log("更新没有改变的殿堂人物，数量：" + stayList.size());
                for (SimpleToken token : stayList) {
                    String objectId = token.getValue("objectId");
                    token.removeValue("objectId");
                    token.removeValue("createdAt");
                    token.removeValue("updatedAt");
                    connection.updateObject("NPCPlaceObject", objectId, token.toJSONString());
                }
            }
        }catch (Exception e){
            Content.error("计算殿堂排序出错", e);
        }
    }

    private void calculatePalaceAward(Map<Long, SimpleToken> levMap) {
        Content.log("计算殿堂奖励");
        List<Long> levSet = new ArrayList<>(levMap.keySet());
        Collections.sort(levSet);
        for(int i=0; i<levSet.size();i++){
            int ranking = levSet.size() - i;

            SimpleToken token = levMap.get(levSet.get(i));
            int vip = StringUtils.toInt(token.getValue("pay"));
            String award = "";
            if(ranking == levSet.size()){
                award = "RandomPortal-RandomPortal-RandomPortal-RandomPortal-RandomPortal-TenMMeat";
            }else {
                switch (ranking) {
                    case 1:
                        award = "RandomGoods-RandomGoods-RandomGoods-RandomGoods-TenMMeat-Evocation-VIP";
                        vip /= 4;
                        break;
                    case 2:
                        award = "RandomGoods-RandomGoods-TenMMeat-Evocation-VIP";
                        vip /= 3;
                        break;
                    case 3:
                        award = "RandomGoods--TenMMeat-Evocation-VIP";
                        vip /= 2;
                        break;
                    default:
                        award = "RandomGoods-TenMMeat-Evocation";
                }
            }
            String vipAward = "";
            while(vip > 0){
                vipAward += "-Medallion";
                if(vip > 151){
                    vip -= 100;
                }else if(vip > 51){
                    vip -= 35;
                }else if(vip > 17){
                    vip -= 8;
                }else if(vip > 9){
                    vip -= 3;
                }else {
                    vip --;
                }
            }
            if(StringUtils.isNotEmpty(vipAward)){
                award += vipAward;
            }
            token.setValue("award", award);
        }
    }

    private void calculateWeight(Map<Long, SimpleToken> levMap, List<SimpleToken> stayList) {
        Content.log("计算殿堂权重");
        if(!stayList.isEmpty()){
            for(SimpleToken token : stayList){
                int weight = StringUtils.toInt(token.getValue("weight"));
                long lev = StringUtils.toLong(token.getValue("lev"));
                if(weight > 7){
                    Content.log("删除旧殿堂人物（长期无活动）：" + token.getValue("name"));
                    connection.deleteObject("NPCPlaceObject", token.getValue("objectId"));
                    levMap.remove(lev);
                }else{
                    if(StringUtils.isNotEmpty(token.getValue("award"))){
                        weight += 2;
                    }else{
                        weight +=1;
                    }
                    token.setValue("weight", weight);
                }
            }
        }
    }

    public static void main(String...args){
        RestConnection connection = new RestConnection();
        PalaceSort palaceSort = new PalaceSort(connection);
        palaceSort.run();
    }
}
