package skill;

import pet.NetPet;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/15/2015.
 */
public class HitSkill extends AtkSkill {

    public HitSkill(NetPet me, long count) {
        super(me, count);
        setRate(20f);
    }

    public boolean perform() {
        return random.nextInt(100) < 20;
    }

    @Override
    public boolean release(NetPet target, long harm) {
        return false;
    }

    @Override
    public String getName() {
        return "重击";
    }

    @Override
    public long getHarm(NetPet target) {
        long l1 = getRandom().nextLong((count / 1000) * 2 + 1) + 1;
        long l = me.getAtk(false) * l1 - target.getDef(false);
        if(l <= 0) l = me.getLev();
        return l;
    }


}
