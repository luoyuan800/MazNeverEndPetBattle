package skill;

import pet.NetPet;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/23/2015.
 */
public class OriginalPower extends DefSkill {
    public OriginalPower(NetPet me, long count) {
        super(me, count);
    }

    @Override
    public boolean release(NetPet target, long harm) {
        me.addMessage(me.getFormatName() + "恢复了全部生命值");
        me.setHp(me.getuHp());
        return false;
    }

    @Override
    public String getName() {
        return "原能力";
    }
}
