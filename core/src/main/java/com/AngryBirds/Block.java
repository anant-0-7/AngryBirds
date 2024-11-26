package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class Block implements Serializable {
    transient private  Texture texture;
    transient private Body body;
    int health;
    boolean isMarkedDestructed=false;
    float x;
    float y;

    public Block(World world, float x, float y, float width, float height,int health) {
        this(world, null, x, y, width, height, health);
    }

    public Block(World world, Texture texture, float x, float y, float width, float height,int health) {
        this.texture = texture;
        this.health=health;
        this.x=x;
        this.y=y;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x / 100f, y / 100f);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 200f, height / 200f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        body.createFixture(fixtureDef);
        body.setUserData(this);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        float x = body.getPosition().x * 100 - texture.getWidth() / 2f;
        float y = body.getPosition().y * 100 - texture.getHeight() / 2f;

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
            body = null;
        }
    }

    public void setPosition(float x, float y){
        this.x=x;
        this.y=y;
        body.setTransform(x/100f, y/100f,0);
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }

    public Body getBody() {
        return body;
    }
    public void setBody(Body body) {
        this.body=body;
    }
    public void changeState(Block oldBlock){
        this.health=oldBlock.health;
        this.isMarkedDestructed= oldBlock.isMarkedDestructed;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}
