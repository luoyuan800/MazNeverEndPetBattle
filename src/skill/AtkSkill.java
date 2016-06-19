package skill;


import pet.NetPet;
import util.Random;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/21/2015.
 */
public abstract class AtkSkill extends NSkill {

    public AtkSkill(NetPet me, long count){
        this.me = me;
        this.count = count;
        random = new Random();
        float rate = count/1000 + 2;
        if(rate > 25){
            rate = 25;
        }
        setRate(rate);
    }
    public boolean release(NetPet target, long harm) {
        return false;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public void setRandom(Random random) {
        this.random = random;
    }

    public NetPet getMe() {
        return me;
    }

    public void setMe(NetPet me) {
        this.me = me;
    }
}
