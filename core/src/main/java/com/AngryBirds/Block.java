package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Block {
    private final Texture texture;
    private final Body body;

    public Block(World world, Texture texture, float x, float y, float width, float height) {
        this.texture = texture;

        // Create body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody; // Dynamic body for interaction
        bodyDef.position.set(x / 100f, y / 100f); // Position in meters

        // Create the body
        body = world.createBody(bodyDef);

        // Define the block's shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 200f, height / 200f); // Half-width/height in meters

        // Create the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f; // Adjust for realistic weight
        fixtureDef.friction = 0.5f; // Sliding friction
        fixtureDef.restitution = 0.0f; // Minimal bounce

        body.createFixture(fixtureDef);
        shape.dispose(); // Dispose shape to free memory
    }

    public void render(SpriteBatch batch) {
        // Get the position and rotation from the Box2D body
        float x = body.getPosition().x * 100 - texture.getWidth() / 2;
        float y = body.getPosition().y * 100 - texture.getHeight() / 2;

        // Draw the texture with position and rotation
        batch.begin();
        batch.draw(texture, x, y, texture.getWidth(), texture.getHeight());
        batch.end();
    }

    public void dispose(World world) {
        world.destroyBody(body); // Remove the body from the Box2D world
        texture.dispose(); // Dispose of the texture
    }

    public Body getBody() {
        return body;
    }
}
