package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Pig {

    private float x, y;
    private int health;
    private Texture texture;

    public Pig(Texture texture, float x, float y) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Image getImage() {
        return new Image(texture);
    }


    public void dispose() {
        texture.dispose();
    }

}
