package skill;

import pet.NetPet;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class MultiSkill extends AtkSkill {
    private long i, j;
    public MultiSkill(NetPet me, long count) {
        super(me, count);
        i = count/1000 + 2;
        j = count/1000 + 2 + 60;
        setRate(30f);
    }

    @Override
    public String getName() {
        return "多重攻击";
    }

    @Override
    public long getHarm(NetPet target) {
        long c = random.nextLong(i) +1;
        long l = me.getAtk() - target.getDef();
        if(l <= 0) l = me.getLev();
        Double l1 = l * (j / 100d) * c;
        long harm = l1.longValue();
        me.addMessage(me.getFormatName() + "攻击了" + c + "次");
        return harm;
    }
}
