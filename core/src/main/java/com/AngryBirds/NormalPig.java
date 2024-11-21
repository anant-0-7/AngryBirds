package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class NormalPig extends Pig {


    public NormalPig(World world, float x, float y) {
        super(new Texture("pig.png"), world, x, y);

    }


}
