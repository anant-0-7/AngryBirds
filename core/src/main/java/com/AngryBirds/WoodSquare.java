package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class WoodSquare extends Block {

    public WoodSquare(World world, float x, float y){
        super(world, new Texture("woodSquare.png"), x, y, 50, 50,3);
    }
}
