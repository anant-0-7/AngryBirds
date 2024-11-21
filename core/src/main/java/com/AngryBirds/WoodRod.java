package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class WoodRod extends Block {
    public WoodRod(World world, float x, float y,Texture t) {
        super(world,t, x, y, t.getWidth(),t.getHeight());
    }
}
