package skill;

import pet.NetPet;
import util.StringUtils;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class ReboundSkill extends DefSkill {
    public ReboundSkill(NetPet me, long count) {
        super(me, count);
        setRate(20);
    }

    @Override
    public boolean release(NetPet target, long harm) {
        Double re = (harm * (50d + getRate() * 5)/100d);
        target.addHp(-re.longValue());
        harm = harm - re.longValue();
        if(harm < 0) harm = 0;
        me.addMessage(me.formateName() + "反弹了" + StringUtils.formatNumber(re.longValue()) + "点伤害");
        me.addMessage(me.formateName() + "受到了" + StringUtils.formatNumber(harm) + "点伤害");
        me.addHp(-harm);
        return true;
    }

    @Override
    public String getName() {
        return "反弹";
    }
}
