package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class WoodRod extends Block {
    public WoodRod(World world, float x, float y) {
        super(world, new Texture("woodRod.png"), x, y, 50, 300); // Example dimensions
    }
}
