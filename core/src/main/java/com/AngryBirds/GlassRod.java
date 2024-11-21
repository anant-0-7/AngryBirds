package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class GlassRod extends Block {

    public GlassRod(World world, float x, float y){
        super(world, new Texture("glassRod.png"), x, y, 50, 300,3);
    }
}
