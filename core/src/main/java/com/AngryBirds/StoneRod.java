package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class StoneRod extends Block {

    public StoneRod(World world, float x, float y){
        super(world, new Texture("stoneRod.png"), x, y, 50, 300);
    }
}
