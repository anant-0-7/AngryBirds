package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class PanPig extends Pig {


    public PanPig(World world, float x, float y) {
        super(new Texture("panpig.png"), world, x, y,1);

    }


}
