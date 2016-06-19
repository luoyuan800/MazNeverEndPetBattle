package pet;

import java.util.List;

import json.SimpleToken;
import skill.AtkSkill;
import skill.DefSkill;
import skill.NSkill;
import util.Content;
import util.Element;
import util.Random;
import util.StringUtils;

/**
 * Created by gluo on 6/17/2016.
 */
public class NetPet{
    private String objectId;
    private String createdAt;
    private String updatedAt;
    protected Long uHp;
    private Long hp;
    protected Long def;
    protected Long atk;
    private Integer hit;
    private Integer parry;
    private String name;
    private Integer dodge;
    private Element element;
    private Long lev;
    private Long intimacy;
    private Long deathCount;
    private String type;
    private String id;
    private boolean onUsed;
    private String fName;
    private String mName;
    private Integer sex;
    private String color;
    private Long atk_rise ;
    private Long def_rise ;
    private Long hp_rise ;
    private String owner;
    private String ownerId;
    private Float eggRate;
    private Integer index;
    private Integer image;
    private String tag;
    private Integer store;
    private Integer race;
    private Integer point = 0;
    private Integer ranking = 0;
    private List<String> battleIds;
    private String keeperId;
    private Long victor = 0L;
    private Long lost = 0L;
    private Long metare =0L;
    private long skillCount = 1L;
    private Random random;
    private NSkill skill;

    public void setNSkill(NSkill skill){
        this.skill = skill;
    }

    public float calculateRate(){
        long total = lost + victor;
        if(total <= 0){
            total = 1;
        }
        return victor*100 / total;
    }

    public NetPet(SimpleToken token){
        this.uHp = token.getValue("uHp");
        this.hp = token.getValue("uHp");
        this.def = token.getValue("def");
        this.atk = token.getValue("atk");
        this.hit = token.getValue("hit");
        this.parry = token.getValue("parry");
        this.name = token.getValue("name");
        this.dodge = token.getValue("doge");
        this.element = Element.valueOf(token.<String>getValue("element"));
        this.type = token.getValue("type");
        this.id = token.getValue("id");
        this.sex = token.getValue("sex");
        this.color = token.getValue("color");
        this.atk_rise = token.getValue("atk_rise");
        this.def_rise = token.getValue("def_rise");
        this.hp_rise = token.getValue("hp_rise");
        this.index = token.getValue("index");
        this.race = token.getValue("race");
        this.battleIds = token.getValue("battleIds");
        this.objectId = token.getValue("objectId");
        this.updatedAt = token.getValue("updateAt");
        this.createdAt = token.getValue("createdAt");
        this.metare = token.getValue("metare");
        String skill = token.getValue("skill");
        if(skill!=null){
            String[] skill_count = skill.split("_");
            if(skill_count .length > 1) {
                this.skillCount = StringUtils.toLong(skill_count[1]);
            }
        }
        random = new Random();
    }

    public void addHp(long hp){
        this.hp += hp;
    }
    public Long getuHp() {
        return uHp;
    }

    public void setuHp(Long uHp) {
        this.uHp = uHp;
    }

    public Long getDef() {
        return def;
    }

    public void setDef(Long def) {
        this.def = def;
    }

    public Long getAtk() {
        return atk;
    }

    public void setAtk(Long atk) {
        this.atk = atk;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public Integer getParry() {
        return parry;
    }

    public void setParry(Integer parry) {
        this.parry = parry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDodge() {
        return dodge;
    }

    public void setDodge(Integer dodge) {
        this.dodge = dodge;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Long getLev() {
        return lev;
    }

    public void setLev(Long lev) {
        this.lev = lev;
    }

    public Long getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Long intimacy) {
        this.intimacy = intimacy;
    }

    public Long getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(Long deathCount) {
        this.deathCount = deathCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOnUsed() {
        return onUsed;
    }

    public void setOnUsed(boolean onUsed) {
        this.onUsed = onUsed;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getAtk_rise() {
        return atk_rise;
    }

    public void setAtk_rise(Long atk_rise) {
        this.atk_rise = atk_rise;
    }

    public Long getDef_rise() {
        return def_rise;
    }

    public void setDef_rise(Long def_rise) {
        this.def_rise = def_rise;
    }

    public Long getHp_rise() {
        return hp_rise;
    }

    public void setHp_rise(Long hp_rise) {
        this.hp_rise = hp_rise;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Float getEggRate() {
        return eggRate;
    }

    public void setEggRate(Float eggRate) {
        this.eggRate = eggRate;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getRace() {
        return race;
    }

    public void setRace(Integer race) {
        this.race = race;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public List<String> getBattleIds() {
        return battleIds;
    }

    public void setBattleIds(List<String> battleIds) {
        this.battleIds = battleIds;
    }

    public String getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(String keeperId) {
        this.keeperId = keeperId;
    }

    public String formateName() {
        return "<font color=\"" + getColor() + "\">" + getName() + (sex == 0 ? "♂" : "♀") + "</font>(" + getElement() + ")";
    }
    public String getFormatName() {
        return formateName();
    }

    public Long getVictor() {
        return victor;
    }

    public void setVictor(Long victor) {
        this.victor = victor;
    }

    public Long getLost() {
        return lost;
    }

    public void setLost(Long lost) {
        this.lost = lost;
    }

    public Long getMetare() {
        return metare;
    }

    public void setMetare(Long metare) {
        this.metare = metare;
    }

    public Long getHp() {
        return hp;
    }

    public void setHp(Long hp) {
        this.hp = hp;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addMessage(String msg){
        Content.addMessage(msg);
    }

    public void normalAtk(NetPet target, long harm, Element element) {
        if (target.isDodge()) {
            addMessage(target.getFormatName() + "闪开了" + getFormatName() + "的攻击。");
        } else {
            NSkill skill = target.getDefSkill();
            boolean skip = false;
            if (skill != null) {
                addMessage(target.getFormatName() + "使用了技能" + skill.getName());
                element = skill.getElement();
                skip = skill.release(this, harm);
            }
            if(!skip){
                harm = judgeElement(target, harm, element);
                if(harm <= 0) harm = random.nextLong(1024L) + 1;
                addMessage(target.getFormatName() + "受到了<font color=\"red\">" + harm + "</font>点伤害");
                target.addHp(-harm);
            }
        }
    }

    void atk(NetPet target) {
        NSkill skill = getAtkSkill();
        Element element = this.element;
        long harm = 0;
        if (skill != null) {
            addMessage(getFormatName() + "使用技能" + skill.getName());
            harm = skill.getHarm(target);
            element = skill.getElement();
            if(harm < 0){
                target.normalAtk(this, -harm, element);
                return;
            }
        } else {
            addMessage(getFormatName() + "攻击了" + target.getFormatName());
            harm = getAtk() - target.getDef();
            if(harm < 0){
                if(random.nextLong(100) < 5){
                    addMessage(getFormatName() + "击穿了" + target.getFormatName() + "的防御");
                    harm = atk;
                }else{
                    harm = 0;
                }
            }
        }
        normalAtk(target, harm, element);
    }
    private long judgeElement(NetPet target, long harm, Element meElement) {
        if (meElement.restriction(target.getElement())) {
            harm *= 1.5;
        }
        if (target.getElement().restriction(meElement)) {
            harm *= 0.8;
        }
        return harm;
    }

    public boolean isDodge() {
        return random.nextInt(1000) < dodge;
    }

    public DefSkill getDefSkill(){
        if(skill instanceof DefSkill && skill.perform()){
            return (DefSkill) skill;
        }
        return null;
    }
    public AtkSkill getAtkSkill(){
        if(skill instanceof AtkSkill && skill.perform()){
            return (AtkSkill) skill;
        }
        return null;
    }

    public long getSkillCount() {
        return skillCount;
    }

    public void setSkillCount(long skillCount) {
        this.skillCount = skillCount;
    }
}
