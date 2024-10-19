package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Slingshot {
    private Texture texture;
    private Sprite sprite;
    private float x, y;

    public Slingshot(Texture texture, float x, float y) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.x = x;
        this.y = y;
        this.sprite.setPosition(x, y);
    }


}
