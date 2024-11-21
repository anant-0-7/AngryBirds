package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class KingPig extends Pig {


    public KingPig(World world, float x, float y) {
        super(new Texture("kingpig.png"), world, x, y);

    }


}
