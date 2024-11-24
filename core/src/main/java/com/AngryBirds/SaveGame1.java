package com.AngryBirds;

public class SaveGame1 extends SaveGame {

    private RedBird redBird;
    private RedBird redBird2;
    private RedBird redBird3;
    private WoodSquare woodSquare;
    private GlassSquare glassSquare;
    private Pig pig;
    private StoneSquare stoneSquare;

    public RedBird getRedBird() {
        return redBird;
    }

    public RedBird getRedBird2() {
        return redBird2;
    }

    public RedBird getRedBird3() {
        return redBird3;
    }

    public WoodSquare getWoodSquare() {
        return woodSquare;
    }

    public GlassSquare getGlassSquare() {
        return glassSquare;
    }

    public Pig getPig() {
        return pig;
    }

    public StoneSquare getStoneSquare() {
        return stoneSquare;
    }

    public SaveGame1(RedBird redBird, RedBird redBird2, RedBird redBird3, WoodSquare woodSquare, GlassSquare glassSquare, Pig pig, StoneSquare stoneSquare, int curr) {
        level = 1;
        this.curr = curr;
        this.redBird = redBird;
        this.redBird2 = redBird2;
        this.redBird3 = redBird3;
        this.woodSquare = woodSquare;
        this.glassSquare = glassSquare;
        this.pig = pig;
        this.stoneSquare = stoneSquare;
    }


}
