package util;

/**
 * Created by gluo on 6/20/2016.
 */
public enum Goods {
    Decide("弑神"), Masimm("灭仙"), Alayer("除魔"), Painkiller("斩妖"),Exorcism("驱鬼"), Chaos("混沌"),
    Double("替身"),ShellBell("贝壳铃"),ExpertBelt("达人腰带"),DestinyKnot("红线"),ShedShell("置物交换"),Shell("闲置交换"),
    BrightPowder("闪光背心"),SafeFile("防弹背心"),KingCertificate("王者之证"), Sunglasses("墨镜");
    private String name;
    Goods(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
