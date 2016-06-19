package skill;

import pet.NetPet;
import util.Element;
import util.Random;

/**
 * Copyright 2015 gluo.
 * ALL RIGHTS RESERVED.
 * Created by gluo on 10/21/2015.
 */
public abstract class NSkill {
    long count;
    protected NetPet me;
    private float rate = 25;
    Random random = new Random();
    public boolean perform(){
        return random.nextInt(100) + random.nextFloat() < rate;
    }
    public abstract boolean release(final NetPet target, long harm);

    public abstract String getName();

    public abstract long getHarm(NetPet target);

    public static NSkill createSkillByName(String name, NetPet me, long count) {
        NSkill skill = null;
        if (name.equals("重击")) {
            skill = new HitSkill(me, count);
        } else if (name.equals("多重攻击")) {
            skill = new MultiSkill(me, count);
        } else if (name.equals("闪避")) {
            skill = new DodgeSkill(me, count);
        } else if (name.equals("勇者之击")) {
            skill = new HeroHit(me, count);

        } else if (name.equals("魔王天赋")) {
            skill = new EvilTalent(me, count);
        } else if (name.equals("龙爪")) {
            skill = new DragonClaw(me, count);
        } else if (name.equals("吐息")) {
            skill = new DragonBreath(me, count);
        } else if (name.equals("沙尘")) {
            skill = new SandStorm(me, count);
        } else if (name.equals("欺诈游戏")) {
            skill = new SwindleGame(me, count);
        } else if (name.equals("闪电")) {
            skill = new Lightning(me, count);

        } else if (name.equals("反弹")) {
            skill = new ReboundSkill(me, count);
        } else if (name.equals("水波")) {
            skill = new WaterWave(me, count);
        } else if (name.equals("虚无吞噬")) {
            skill = new SwallowSkill(me, count);
        } else if (name.equals("原能力")) {
            skill = new OriginalPower(me, count);
        }
        if (skill != null) {
            skill.setMe(me);
        }
        return skill;
    }


    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Element getElement() {
        return me.getElement();
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setMe(NetPet me) {
        this.me = me;
    }
}
