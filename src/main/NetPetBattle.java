package main;

import json.JSON;
import json.SimpleToken;
import pet.NetPet;
import rest.RestConnection;
import skill.NSkill;
import util.Random;

import java.util.ArrayList;
import java.util.List;
import static util.Content.*;

/**
 * Created by gluo on 6/20/2016.
 */
public class NetPetBattle implements Runnable {
    private RestConnection connection;

    public NetPetBattle(RestConnection connection) {
        this.connection = connection;
    }

    public List<NetPet> getNetPetList() {
        List<NetPet> netPetList = new ArrayList<>();
        JSON json = connection.queryObjects("NetPet");
        json.parse();
        for (SimpleToken token : json.getTokens()) {
            NetPet netPet = new NetPet(token);
            netPetList.add(netPet);
        }
        return netPetList;
    }

    public void run() {
        List<NetPet> netPetList = getNetPetList();
        Random random = new Random();
        for (NetPet pet : netPetList) {
            if(pet.getMetare() > 0) {
                getRandomSkill(random, pet);
                int petIndex = random.nextInt(netPetList.size());
                if (netPetList.get(petIndex).equals(pet)) {
                    petIndex--;
                }
                if (petIndex >= 0 && petIndex < netPetList.size()) {
                    NetPet pet2 = netPetList.get(petIndex);
                    if(pet2.getMetare() > 0) {
                        getRandomSkill(random, pet2);
                        battle(pet, pet2);
                        pet.setHp(pet.getuHp());
                        pet2.setHp(pet2.getuHp());
                    }
                }
            }
        }
        for (NetPet netPet : netPetList) {
            connection.updateObject("NetPet", netPet.getObjectId(), netPet.buildToken().toJSONString());
        }
    }

    private void getRandomSkill(Random random, NetPet pet) {
        if(pet.getMetare() > skill_cost) {
            int skillIndex = random.nextInt(NSkill.skills.length);
            NSkill skill = NSkill.createSkillByName(NSkill.skills[skillIndex], pet, pet.getSkillCount());
            pet.setNSkill(skill);
            pet.setMetare(pet.getMetare() - skill_cost);
            addMessage(pet.formateName() + "花费了" + skill_cost + "装备了技能" + skill.getName());
        }
    }

    public void battle(NetPet p1, NetPet p2) {
        SimpleToken battleMsg = new SimpleToken();
        battleMsg.setValue("oneP", p1.formateName());
        battleMsg.setValue("twoP", p2.formateName());
        addMessage(p1.formatDetail());
        addMessage("VS.");
        addMessage(p2.formatDetail());
        boolean atk = true;
        while (p1.getHp() > 0 && p2.getHp() > 0) {
            if (atk) {
                p1.atk(p2);
            } else {
                p2.atk(p1);
            }
            atk = !atk;
        }
        if(p2.getHp() <= 0){
            addMessage(p1.formateName() + "击败了" + p2.formateName());
            long mate = p2.getMetare() / 10;
            p2.setMetare(p2.getMetare() - mate);
            addMessage(p1.formateName() + "从" + p2.formateName() + "身上掠夺了" + mate + "锻造点");
            if(p1.getMetare() > Integer.MAX_VALUE){
                addMessage(p1.formateName() + "身上携带的锻造点已经到达上限了，所以丢弃了刚刚掠夺的锻造点。");
            }else {
                p1.setMetare(p1.getMetare() + mate);
            }
            if(mate < 200){
                addMessage(p1.formateName() + "藐视着" + p2.formateName() + "说：穷鬼！");
            }
        }else{
            long mate = p1.getMetare() / 10;
            p1.setMetare(p1.getMetare() - mate);
            addMessage(p2.formateName() + "从" + p1.formateName() + "身上掠夺了" + mate + "锻造点");
            if(p2.getMetare() > Integer.MAX_VALUE){
                addMessage(p2.formateName() + "身上携带的锻造点已经到达上限了，所以丢弃了刚刚掠夺的锻造点。");
            }else {
                p2.setMetare(p2.getMetare() + mate);
            }
            if(mate < 200){
                addMessage(p2.formateName() + "藐视着" + p1.formateName() + "说：穷鬼！");
            }
        }
        String msg = getMessage(true);
        if (msg != null) {
            battleMsg.setValue("msg", msg);
        }
        JSON json = connection.addObject("PetBattleMsg", battleMsg.toJSONString());
        json.parse();
        if (!json.getTokens().isEmpty()) {
            SimpleToken addResult = json.getTokens().get(0);
            String objectId = addResult.getValue("objectId");
            if (objectId != null) {

                p1.addBattleMsg(objectId);
                p2.addBattleMsg(objectId);
            }
        }
    }
}
