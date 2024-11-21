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
    private boolean isDragging = false; // Tracks if bird is being dragged
    private Vector2 slingshotPosition;  // Position of slingshot
    private float maxDragDistance = 2.0f; // Max drag distance in meters

    public Bird(Texture texture, World world, float x, float y) {
        this.texture = texture;
        this.slingshotPosition = new Vector2(x / 100f, y / 100f);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(slingshotPosition);

        body = world.createBody(bodyDef);


        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void updatePosition() {
        if (inSlingshot) {
            if (Gdx.input.isTouched()) {
                float mouseX = Gdx.input.getX() / 100f;
                float mouseY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / 100f;
                Vector2 currentMousePosition = new Vector2(mouseX, mouseY);

                if (!isDragging) {
                    if (currentMousePosition.dst(slingshotPosition) <= maxDragDistance) {
                        isDragging = true;
                    }
                }

                if (isDragging) {
                    Vector2 direction = currentMousePosition.sub(slingshotPosition);
                    if (direction.len() > maxDragDistance) {
                        direction.nor().scl(maxDragDistance);
                    }
                    body.setTransform(slingshotPosition.cpy().add(direction), 0);
                }
            } else if (isDragging) {
                Vector2 dragVector = slingshotPosition.cpy().sub(body.getPosition());
                Vector2 launchVelocity = dragVector.scl(15f); // Adjust scaling for force

                body.setLinearVelocity(launchVelocity);
                inSlingshot = false;
                isDragging = false;
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(texture, body.getPosition().x * 100 - 50, body.getPosition().y * 100 - 50, 100, 100);
        spriteBatch.end();
    }

    public void resetToSlingshot() {
        inSlingshot = true;
        body.setTransform(slingshotPosition, 0);
        body.setLinearVelocity(0, 0);
    }

    public void dispose() {
        texture.dispose();
    }
}
