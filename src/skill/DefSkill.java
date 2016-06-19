package skill;

import pet.NetPet;
import util.Random;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/21/2015.
 */
public abstract class DefSkill extends NSkill {
    public DefSkill(NetPet me, long count){
        this.me = me;
        this.count = count;
        random = new Random();
        float rate = count/1000 + 2;
        if(rate > 25){
            rate = 25;
        }
        setRate(rate);
    }
    public long getHarm(NetPet target){
        return 0;
    }
}
