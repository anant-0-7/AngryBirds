package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Block {
    private  Texture texture;
    private Body body;
    int health;
    boolean isMarkedDestructed=false;
    public Block(World world, Texture texture, float x, float y, float width, float height,int health) {
        this.texture = texture;
        this.health=health;

        // Create body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody; // Use Kinematic or Dynamic based on interaction
        bodyDef.position.set(x / 100f, y / 100f); // Position in meters

        // Create the body
        body = world.createBody(bodyDef);

        // Define the block's shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 200f, height / 200f); // Half-width and half-height in meters

        // Create the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        body.createFixture(fixtureDef);
        body.setUserData(this);
        shape.dispose(); // Dispose shape to free memory
    }

    public void render(SpriteBatch batch) {
        // Get the position and rotation from the Box2D body
        float x = body.getPosition().x * 100 - texture.getWidth() / 2f;
        float y = body.getPosition().y * 100 - texture.getHeight() / 2f;

        // Draw the texture with position and rotation
        batch.begin();
        batch.draw(texture, x, y, texture.getWidth(), texture.getHeight());
        batch.end();
    }
    public void healthReduce(int strength){
        this.health-=strength;
        if(health<=0){
            this.markForDestruction();
        }
        System.out.println("Block Health is:"+health);

    }

    public void markForDestruction() {
        isMarkedDestructed = true;
    }

    public boolean isMarkedForDestruction() {
        return isMarkedDestructed;
    }

    public void safelyDestroy(World world) {
        if (isMarkedDestructed && body != null) {
            world.destroyBody(body);
            body = null; // Prevent further access
        }
    }

    public void dispose() {
        // Dispose of the texture when the bird is no longer needed
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }

    public Body getBody() {
        return body;
    }
}
