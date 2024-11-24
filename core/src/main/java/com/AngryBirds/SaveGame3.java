package com.AngryBirds;

public class SaveGame3 extends SaveGame {

    private RedBird redBird;
    private YellowBird yellowBird;
    private BlackBird blackBird;
    private WoodSquare woodSquare1;
    private WoodSquare woodSquare2;
    private GlassSquare glassSquare;
    private Pig kingPig;
    private Pig panPig;
    private Pig normPig;
    private StoneSquare stoneSquare;

    public SaveGame3(RedBird redBird, YellowBird yellowBird, BlackBird blackBird, WoodSquare woodSquare1, WoodSquare woodSquare2, GlassSquare glassSquare, Pig kingPig, Pig panPig, Pig normPig, StoneSquare stoneSquare, int curr) {
        this.curr = curr;
        level = 3;
        this.redBird = redBird;
        this.yellowBird = yellowBird;
        this.blackBird = blackBird;
        this.woodSquare1 = woodSquare1;
        this.woodSquare2 = woodSquare2;
        this.glassSquare = glassSquare;
        this.kingPig = kingPig;
        this.panPig = panPig;
        this.normPig = normPig;
        this.stoneSquare = stoneSquare;
    }

    public RedBird getRedBird() {
        return redBird;
    }

    public YellowBird getYellowBird() {
        return yellowBird;
    }

    public BlackBird getBlackBird() {
        return blackBird;
    }

    public WoodSquare getWoodSquare1() {
        return woodSquare1;
    }

    public WoodSquare getWoodSquare2() {
        return woodSquare2;
    }

    public GlassSquare getGlassSquare() {
        return glassSquare;
    }

    public Pig getKingPig() {
        return kingPig;
    }

    public Pig getPanPig() {
        return panPig;
    }

    public Pig getNormPig() {
        return normPig;
    }

    public StoneSquare getStoneSquare() {
        return stoneSquare;
    }
}
