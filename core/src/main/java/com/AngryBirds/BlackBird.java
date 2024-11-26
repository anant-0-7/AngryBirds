package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class BlackBird extends Bird {

    public BlackBird(World world, float x, float y) {

        super(new Texture("blackbird.png"),world, x, y,1);
    }
}
