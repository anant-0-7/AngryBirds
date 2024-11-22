package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class GlassSquare extends Block {

    public GlassSquare(World world, float x, float y){
        super(world, new Texture("glassSquare.png"), x, y, 50, 50,1 );
    }
}
