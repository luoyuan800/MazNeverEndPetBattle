package skill;


import pet.NetPet;
import util.StringUtils;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class EvilTalent extends DefSkill {
    public EvilTalent(NetPet me, long count) {
        super(me, count);
    }

    @Override
    public boolean release(NetPet target, long harm) {
        me.addHp(harm);
        me.addMessage(me.getFormatName() + "将" + StringUtils.formatNumber(harm) + "点伤害转换成了HP恢复.");
        return true;
    }

    @Override
    public String getName() {
        return "魔王天赋";
    }
}
