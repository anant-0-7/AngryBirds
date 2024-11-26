package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class Pig implements Serializable {
    transient private Body body;
    transient private Texture texture;
    int health;
    transient World world;
    boolean isMarkedDestructed=false;
    float x;
    float y;

    public Pig(World world, float x, float y, int health) {
        this(null, world, x, y, health); // Pass `null` for texture
    }

    public Pig(Texture texture, World world, float x, float y,int health) {
        this.texture = texture;
        this.health=health;
        this.world=world;
        this.x=x;
        this.y=y;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x / 100f, y / 100f);

        body = world.createBody(bodyDef);


        CircleShape shape = new CircleShape();
        shape.setRadius(0.4f);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.6f;


        body.createFixture(fixtureDef);
        body.setUserData(this);
        shape.dispose();
    }
    public void healthReduce(int strength){
        this.health-=strength;
        if(health<=0){
            this.markForDestruction();
        }
        System.out.println("Pig health is:"+health);

    }

    public void render(SpriteBatch spriteBatch) {

        if(this.texture==null){
            return;
        }

        spriteBatch.begin();
        spriteBatch.draw(texture, body.getPosition().x * 100 - 40, body.getPosition().y * 100 - 40, 80, 80);
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
            body = null;
        }
    }

    public void dispose() {

        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }

    public void setPosition(float x, float y){
        this.x=x;
        this.y=y;
        body.setTransform(x/100f, y/100f,0);
    }

    public Body getBody() {
        return body;
    }
    public void setBody(Body body) {
        this.body=body;
    }
    public void changeState(Pig oldPig){
        this.health=oldPig.health;
        this.isMarkedDestructed= oldPig.isMarkedDestructed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }
}
