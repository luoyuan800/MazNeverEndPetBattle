package skill;


import pet.NetPet;
import util.StringUtils;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class WaterWave extends DefSkill {
    public WaterWave(NetPet me, long count) {
        super(me, count);
    }

    @Override
    public boolean release(NetPet target, long harm) {
        long reduce = (long)(harm * (getRate()*2 + 50)/100);
        if(reduce >= harm){
            reduce = harm;
            harm = 0;
        }else{
            harm = harm - reduce;
        }
        me.addMessage("抵消掉了" + StringUtils.formatNumber(reduce) + "点伤害");
        me.addHp(-harm);
        me.addMessage(me.getFormatName() + "受到了" + StringUtils.formatNumber(harm) + "点伤害");
        return true;
    }

    @Override
    public String getName() {
        return "水波";
    }
}
