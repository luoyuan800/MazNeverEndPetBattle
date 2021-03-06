package skill;

import pet.NetPet;
import util.Element;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class DragonClaw extends AtkSkill {
    public DragonClaw(NetPet me, long count) {
        super(me, count);
    }

    @Override
    public String getName() {
        return "龙爪";
    }

    @Override
    public long getHarm(NetPet target) {
        long harm = me.getAtk(false);
        if(me.getElement().isReinforce(Element.金)){
            harm *= 2;
        }else if(me.getElement().restriction(Element.金)){
            harm *= 0.8;
        }
        long l = harm - target.getDef(false);
        if(l <= 0) l = me.getLev();
        return l;
    }

    @Override
    public Element getElement(){
        return Element.金;
    }
}
