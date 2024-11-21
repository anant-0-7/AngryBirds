package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class RedBird extends Bird {

    public RedBird(World world, float x, float y) {
        super(new Texture("redbird.png"),world, x, y,1);
    }
}
