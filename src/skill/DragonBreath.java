package skill;

import pet.NetPet;
import util.Element;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/22/2015.
 */
public class DragonBreath extends AtkSkill {
    public DragonBreath(NetPet me, long count) {
        super(me, count);
    }

    @Override
    public String getName() {
        return "吐息";
    }

    @Override
    public long getHarm(NetPet target) {
        long harm = me.getAtk(false);
        if(me.getElement().restriction(Element.火)){
            harm *= 0.8;
        }
        long l = harm - target.getDef(false);
        if(l <= 0) l = me.getAtk_rise();
        if(Element.火.restriction(target.getElement())){
            l += target.getuHp();
        }
        return l;
    }

    @Override
    public Element getElement(){
        return Element.火;
    }
}
