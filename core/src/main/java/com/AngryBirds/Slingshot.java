package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Slingshot {
    private Texture texture;
    private float x, y;

    public Slingshot(float x, float y) {
        this.texture = new Texture("slingshot.png");
        this.x = x;
        this.y = y;
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
