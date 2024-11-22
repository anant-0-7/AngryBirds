package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Pig {
    private Body body;
    private Texture texture;
    int health;
    World world;
    boolean isMarkedDestructed=false;

    public Pig(Texture texture, World world, float x, float y,int health) {
        // Load pig texture
        this.texture = texture;
        this.health=health;
        this.world=world;

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


        body.createFixture(fixtureDef);
        body.setUserData(this);
        shape.dispose(); // Dispose shape to free resources
    }
    public void healthReduce(int strength){
        this.health-=strength;
        if(health<=0){
            this.markForDestruction();
        }
        System.out.println("Pig health is:"+health);

    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(texture, body.getPosition().x * 100 - 40, body.getPosition().y * 100 - 40, 80, 80); // Render at body position
        spriteBatch.end();
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

    public void setPosition(float x, float y){
        body.setTransform(x/100f, y/100f,0);
    }

    public Body getBody() {
        return body; // Expose the Box2D body for collision detection
    }
}
