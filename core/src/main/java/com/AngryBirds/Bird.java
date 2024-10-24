package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class Bird {
    private Texture texture;
    private Sprite sprite;
    private float x, y;
    private float velocityX, velocityY;

    public Bird(Texture texture, float x, float y) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.x = x;
        this.y = y;

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return new Image(texture);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public void dispose() {
        texture.dispose();
    }
}
