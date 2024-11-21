package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class YellowBird extends Bird {


    public YellowBird(World world, float x, float y) {

        super(new Texture("yellowbird.png"), world,  x, y,2);
    }
}
