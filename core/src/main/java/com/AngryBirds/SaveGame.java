package com.AngryBirds;

import java.io.Serializable;

public class SaveGame implements Serializable {

    protected int level;
    protected int curr;

    public int getLevel() {
        return level;
    }

    public int getCurr() {
        return curr;
    }

}
