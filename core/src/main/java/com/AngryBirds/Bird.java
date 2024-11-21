package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;

public class Bird {
    private Body body;
    private Texture texture;

    public Bird(Texture texture, World world, float x, float y) {
        this.texture = texture;

        // Create the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / 100f, y / 100f); // Convert to meters

        body = world.createBody(bodyDef);

        // Create the shape
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f); // Radius in meters

        // Create the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.5f; // Bounce

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void updatePosition() {
        // Get the mouse position in screen coordinates
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();

        // Convert screen coordinates to world coordinates (Box2D uses meters, screen uses pixels)
        float worldX = mouseX / 100f; // Convert to meters
        float worldY = (Gdx.graphics.getHeight() - mouseY) / 100f; // Invert Y-axis and convert to meters

        // Set the bird's position to the mouse's position in world coordinates
        body.setTransform(worldX, worldY, body.getAngle());
    }

    public void render(SpriteBatch spriteBatch) {



        spriteBatch.begin();
        // Draw the bird at its current position (scaled back to pixels)
        spriteBatch.draw(texture, body.getPosition().x * 100 - 50, body.getPosition().y * 100 - 50, 100, 100); // Convert meters to pixels
        spriteBatch.end();
    }

    public Body getBody() {
        return body;
    }


    public void dispose() {
        texture.dispose();
    }
}
