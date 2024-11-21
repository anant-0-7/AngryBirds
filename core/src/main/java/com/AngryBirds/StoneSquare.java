package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class StoneSquare extends Block {

    public StoneSquare(World world, float x, float y){
        super(world, new Texture("stoneSquare.png") , x, y, 50, 50,5);
    }
}
