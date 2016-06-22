package main.task;

import json.JSON;
import json.SimpleToken;
import pet.NetPet;
import rest.RestConnection;
import skill.NSkill;
import util.Content;
import util.Goods;
import util.Random;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static util.Content.*;

/**
 * Created by gluo on 6/20/2016.
 */
public class NetPetBattle implements Runnable {
    private RestConnection connection;
    private Random random;

    public NetPetBattle(RestConnection connection) {
        this.connection = connection;
    }

    public List<NetPet> getNetPetList() {
        List<NetPet> netPetList = new ArrayList<>();
        JSON json = connection.queryObjects("NetPet");
        json.parse();
        for (SimpleToken token : json.getTokens()) {
            NetPet netPet = new NetPet(token);
            if(netPet.getMetare() > 0) {
                netPetList.add(netPet);
            }
        }
        return netPetList;
    }

    public void run() {
        try {
            Content.log("开始运行竞技场" + new Date());
            Content.log("拉取参战宠物信息");
            List<NetPet> netPetList = getNetPetList();
            random = new Random();
            Content.log("战斗开始");
            for (NetPet pet : netPetList) {
                if (pet.getMetare() > 0) {
                    getRandomSkill(random, pet);
                    int petIndex = random.nextInt(netPetList.size());
                    if (netPetList.get(petIndex).equals(pet)) {
                        petIndex--;
                    }
                    if (petIndex >= 0 && petIndex < netPetList.size()) {
                        NetPet pet2 = netPetList.get(petIndex);
                        if (pet2.getMetare() > 0) {
                            getRandomSkill(random, pet2);
                            battle(pet, pet2);
                            pet.setHp(pet.getuHp());
                            pet2.setHp(pet2.getuHp());
                        }
                    }
                }
            }
            Content.log("战斗结束");
            Content.log("计算排名");
            netPetList.sort(new Comparator<NetPet>() {
                @Override
                public int compare(NetPet o1, NetPet o2) {
                    return o1.getPoint() < o2.getPoint() ? 1 : Objects.equals(o1.getPoint(), o2.getPoint()) ? 0 : -1;
                }
            });
            Content.log("更新宠物数据");
            int ranking = 1;
            for (int i = 0; i < netPetList.size(); i++) {
                NetPet netPet = netPetList.get(i);
                if (i > 0) {
                    if (!Objects.equals(netPet.getPoint(), netPetList.get(i - 1).getPoint())) {
                        ranking++;
                    }
                }
                netPet.setRanking(ranking);
                connection.updateObject("NetPet", netPet.getObjectId(), netPet.buildToken().toJSONString());
            }
            Content.log("宠物数据更新完毕" + new Date());
        } catch (Exception e){
            e.printStackTrace();
            Content.error("NetPetBattleError", e);
        }
    }

    private void getRandomSkill(Random random, NetPet pet) {
        if(pet.getMetare() > skill_cost) {
            int skillIndex = random.nextInt(NSkill.skills.length);
            NSkill skill = NSkill.createSkillByName(NSkill.skills[skillIndex], pet, pet.getSkillCount());
            pet.setNSkill(skill);
            if(pet.getGoods() == Goods.Sunglasses){
                addMessage(pet.formateName() + "带着" + pet.getGoods().getName());
            }
            pet.setMetare(pet.getMetare() - skill_cost/2);
            addMessage(pet.formateName() + "花费了" + skill_cost/2 + "装备了技能" + skill.getName());
        }else{
            addMessage(pet.formateName() + "因为没钱所以没有购买技能技能。");
        }
    }

    public void battle(NetPet p1, NetPet p2) {
        Long originalAtk = null;
        if(p2.getGoods()!=null && p1.getGoods() == Goods.ShedShell && random.nextInt(100) < 3){
            addMessage(p1.formateName() + "用" + p1.getGoods().getName() + "和" + p2.formateName() + "交换到了" + p2.getGoods());
            p1.setGoods(p2.getGoods());
            p2.setGoods(Goods.ShedShell);
        }
        if(p1.getGoods() == Goods.DestinyKnot && !Objects.equals(p1.getSex(), p2.getSex()) && random.nextInt(100) < 3){
            originalAtk = p2.getAtk(true);
            p2.setAtk((long)((double)p2.getAtk(true) * 0.7));
            addMessage("因为" + p1.getGoods().getName() + "的效果" + p2.formateName() + "的攻击力降低了30%。");
        }
        SimpleToken battleMsg = new SimpleToken();
        battleMsg.setValue("oneP", p1.formateName());
        battleMsg.setValue("twoP", p2.formateName());
        addMessage(p1.formatDetail());
        addMessage("VS.");
        addMessage(p2.formatDetail());
        boolean atk = true;
        int turn = 1;
        while (p1.getHp() > 0 && p2.getHp() > 0) {
            addMessage("-----------");
            if (atk) {
                p1.atk(p2);
            } else {
                p2.atk(p1);
            }
            if(p1.getGoods() == Goods.KingCertificate && p1.getIndex() > p2.getIndex()){
                addMessage(p1.formateName() + "亮出了" + p1.getGoods().getName() + "震吓住了低阶位的" + p2.formateName() + "," + p2.formateName() + "因为恐惧无法攻击。");
            }else {
                atk = !atk;
            }
            if(turn <= 2 && p1.getGoods() == Goods.Double && p1.getHp() <= 0){
                addMessage(p1.formateName() + "因为'替身'的效果恢复了10%的生命");
                p1.setHp(p1.getuHp() + (long)(p1.getuHp().doubleValue() * 0.1));
            }
            turn ++;
            addMessage("-----------");
        }
        if(p2.getHp() <= 0){
            addMessage(p1.formateName() + "击败了" + p2.formateName());
            long mate = p2.getMetare() / 10;
            if(mate <= 0){
                mate = 1;
            }
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
            p1.setPoint(p1.getPoint() + 1);
            p1.setVictor(p1.getVictor() + 1);
            p2.setLost(p2.getLost() + 1);
        }else{
            addMessage(p2.formateName() + "击败了" + p1.formateName());
            long mate = p1.getMetare() / 10;
            if(mate <= 0){
                mate = 1;
            }
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
            p2.setPoint(p2.getPoint() + 1);
            p2.setVictor(p2.getVictor() + 1);
            p1.setLost(p1.getLost() + 1);
        }
        if(p2.getGoods()!=null && p1.getGoods() == Goods.Shell && random.nextInt(100) < 5){
            addMessage(p1.formateName() + "用" + p1.getGoods().getName() + "和" + p2.formateName() + "交换到了" + p2.getGoods());
            p1.setGoods(p2.getGoods());
            p2.setGoods(Goods.Shell);
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
        if(originalAtk!=null){
            p2.setAtk(originalAtk);
        }
    }
}
