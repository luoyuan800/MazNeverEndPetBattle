package skill;


import pet.NetPet;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class DodgeSkill extends DefSkill {
    public DodgeSkill(NetPet me, long count) {
        super(me, count);
        setRate(10f);
    }

    @Override
    public boolean release(NetPet target, long harm) {
        me.addMessage(me.formateName() + "闪避了" + target.formateName() + "的攻击");
        return true;
    }

    @Override
    public String getName() {
        return "闪避";
    }
}
