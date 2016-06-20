package skill;


import pet.NetPet;
import util.Element;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/29/2015.
 */
public class ElementTransForm extends AtkSkill {
    private Element element;
    public ElementTransForm(NetPet me, long count) {
        super(me, count);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public long getHarm(NetPet target) {
        this.element = target.getElement().getRestriction();
        long harm = me.getAtk(false) - target.getDef(false);
        if(harm < 0) harm = random.nextLong(me.getAtk_rise() + 1);
        return harm;
    }

    public Element getElement(){
        return element;
    }
}
