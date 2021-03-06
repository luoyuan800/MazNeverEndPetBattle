package skill;


import pet.NetPet;
import util.Element;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class SandStorm extends AtkSkill {
    public SandStorm(NetPet me, long count) {
        super(me, count);
    }

    @Override
    public String getName() {
        return "沙尘";
    }

    @Override
    public long getHarm(NetPet target) {
        long harm = me.getAtk(false);
        if(me.getElement().restriction(Element.土)){
            harm *= 0.8;
        }
        long l = harm - target.getDef(false);
        if(l <= 0) l = me.getLev();
        me.addHp(l);
        me.addMessage("恢复了" + l + "点HP");
        return l;
    }

    @Override
    public Element getElement(){
        return Element.土;
    }
}
