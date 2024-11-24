package com.AngryBirds;

public class SaveGame2 extends SaveGame {

    private RedBird redBird;
    private YellowBird yellowBird;
    private YellowBird yellowBird2;
    private WoodSquare woodSquare;
    private GlassSquare glassSquare;
    private GlassSquare glassSquare1;
    private Pig pig;
    private Pig pig1;
    private StoneSquare stoneSquare;

    public SaveGame2(RedBird redBird, YellowBird yellowBird, YellowBird yellowBird2, WoodSquare woodSquare, GlassSquare glassSquare, GlassSquare glassSquare1, Pig pig, Pig pig1, StoneSquare stoneSquare, int curr) {
        level = 2;
        this.curr = curr;
        this.redBird = redBird;
        this.yellowBird = yellowBird;
        this.yellowBird2 = yellowBird2;
        this.woodSquare = woodSquare;
        this.glassSquare = glassSquare;
        this.glassSquare1 = glassSquare1;
        this.pig = pig;
        this.pig1 = pig1;
        this.stoneSquare = stoneSquare;
    }

    public RedBird getRedBird() {
        return redBird;
    }

    public YellowBird getYellowBird() {
        return yellowBird;
    }

    public YellowBird getYellowBird2() {
        return yellowBird2;
    }

    public WoodSquare getWoodSquare() {
        return woodSquare;
    }

    public GlassSquare getGlassSquare() {
        return glassSquare;
    }

    public GlassSquare getGlassSquare1() {
        return glassSquare1;
    }

    public Pig getPig() {
        return pig;
    }

    public Pig getPig1() {
        return pig1;
    }

    public StoneSquare getStoneSquare() {
        return stoneSquare;
    }
}
