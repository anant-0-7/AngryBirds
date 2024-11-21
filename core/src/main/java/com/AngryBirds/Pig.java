package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Pig {
    private Body body;
    private Texture texture;

    public Pig(Texture texture, World world, float x, float y) {
        // Load pig texture
        this.texture = texture;

        // Define body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody; // Pigs are dynamic bodies
        bodyDef.position.set(x / 100f, y / 100f); // Convert to meters

        body = world.createBody(bodyDef);

        // Define shape
        CircleShape shape = new CircleShape();
        shape.setRadius(0.4f); // Adjust radius to match pig size (in meters)

        // Define fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.6f; // Lower density for lighter weight
        fixtureDef.restitution = 0.3f; // Slight bounce
        fixtureDef.friction = 0.5f; // Add some friction

        body.createFixture(fixtureDef);
        shape.dispose(); // Dispose shape to free resources
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(texture, body.getPosition().x * 100 - 40, body.getPosition().y * 100 - 40, 80, 80); // Render at body position
        spriteBatch.end();
    }

    public void dispose() {
        texture.dispose(); // Dispose texture to free resources
    }

    public Body getBody() {
        return body; // Expose the Box2D body for collision detection
    }
}
