package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Slingshot {
    private Texture texture;
    private float x, y;

    public Slingshot(float x, float y) {
        this.texture = new Texture("slingshot.png");
        // Load the slingshot texture
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(texture, x, y, 200, 250); // Draw the slingshot at the specified position
        spriteBatch.end();
    }

    public void dispose() {
        texture.dispose(); // Clean up the texture
    }
}
