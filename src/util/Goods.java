package util;

/**
 * Created by gluo on 6/20/2016.
 */
public enum Goods {
    Decide("弑神"), Masimm("灭仙"), Alayer("除魔"), Painkiller("斩妖"),Exorcism("驱鬼"), Chaos("混沌") ;
    private String name;
    Goods(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
