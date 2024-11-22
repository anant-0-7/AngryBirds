package com.AngryBirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;

public class Bird {
    private Body body;
    private Texture texture;
    private boolean inSlingshot = true;
    private boolean isDragging = false;
    private Vector2 slingshotPosition;
    private boolean isLaunched = false;
    // Position of slingshot
    private float maxDragDistance = 2.0f;
    int strength;
    World world;
    boolean isMarkedDestructed=false;

    public Bird(Texture texture, World world, float x, float y,int strength) {
        this.texture = texture;
        this.strength=strength;
        this.world=world;
        this.slingshotPosition = new Vector2(400/100f, 450/100f);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/100f,y/100f);

        body = world.createBody(bodyDef);


        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        body.createFixture(fixtureDef);
        body.setUserData(this);
        shape.dispose();
    }

    public void updatePosition1() {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();

        float worldX = mouseX / 100f;
        float worldY = (Gdx.graphics.getHeight() - mouseY) / 100f;


        body.setTransform(worldX, worldY, body.getAngle());
    }

    public void update() {
        if (!isLaunched) {
            if (Gdx.input.isTouched()) {
                float mouseX = Gdx.input.getX() / 100f;
                float mouseY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / 100f;
                Vector2 currentMousePosition = new Vector2(mouseX, mouseY);

                if (!isDragging && currentMousePosition.dst(slingshotPosition) <= maxDragDistance) {
                    isDragging = true;
                }

                // Drag bird
                if (isDragging) {
                    Vector2 dragOffset = currentMousePosition.sub(slingshotPosition);

                    if (dragOffset.len() > maxDragDistance) {
                        dragOffset.nor().scl(maxDragDistance);
                    }

                    body.setTransform(slingshotPosition.cpy().add(dragOffset), 0);
                }
            }
            else if (isDragging) {
                Vector2 launchVector = slingshotPosition.cpy().sub(body.getPosition());
                float launchIntensity;
                if(strength==1){
                    launchIntensity = launchVector.len() * 5f*strength;
                } else if (strength==2) {
                    launchIntensity = launchVector.len() * 6f*strength;
                }
                else{
                    launchIntensity = launchVector.len() * 7f*strength;
                }


                body.setLinearVelocity(launchVector.nor().scl(launchIntensity));
                isDragging = false;
                isLaunched = true;
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        if(body.getPosition().x*100>1920 || body.getPosition().x*100<0 || body.getPosition().y*100>1080 || body.getPosition().y*100<0){
            markForDestruction();
        }
        spriteBatch.draw(texture, body.getPosition().x * 100 - 50, body.getPosition().y * 100 - 50, 100, 100);
        spriteBatch.end();
    }
    public void updatePosition(){
        inSlingshot = true;
        body.setTransform(400/100f, 450/100f,0);
    }
    public int getStrength(){
        return this.strength;
    }
    public Body getBody(){
        return this.body;
    }
    public void markForDestruction() {
        isMarkedDestructed = true;
    }

    public boolean isMarkedForDestruction() {
        return isMarkedDestructed;
    }

    public void resetToSlingshot() {
        inSlingshot = true;
        body.setTransform(slingshotPosition, 0);
        body.setLinearVelocity(0, 0);
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
}
