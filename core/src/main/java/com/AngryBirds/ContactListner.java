package com.AngryBirds;

import com.badlogic.gdx.physics.box2d.*;

public class ContactListner implements ContactListener {
    World world;
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();


        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if ((userDataA instanceof Bird && userDataB instanceof Block) ||
            (userDataA instanceof Block && userDataB instanceof Bird)) {
            System.out.println("Hello Bird");
            handleBirdBlockCollision(userDataA, userDataB);
        }

        if ((userDataA instanceof Bird && userDataB instanceof Pig) ||
            (userDataA instanceof Pig && userDataB instanceof Bird)) {
            System.out.println("Hello Pig");
            handleBirdPigCollision(userDataA, userDataB);
        }
    }

    private void handleBirdBlockCollision(Object birdObj, Object blockObj) {
        Bird bird = (birdObj instanceof Bird) ? (Bird)birdObj : (Bird)blockObj;
        Block block = (blockObj instanceof Block) ? (Block)blockObj : (Block)birdObj;

        block.healthReduce(bird.getStrength());


        bird.markForDestruction();
    }

    private void handleBirdPigCollision(Object birdObj, Object pigObj) {
        Bird bird = (birdObj instanceof Bird) ? (Bird)birdObj : (Bird)pigObj;
        Pig pig = (pigObj instanceof Pig) ? (Pig)pigObj : (Pig)birdObj;

        // Reduce pig health
        pig.healthReduce(bird.getStrength());


        bird.markForDestruction();
    }

    @Override
    public void endContact(Contact contact) {
        // Not used in this implementation
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Not used in this implementation
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Not used in this implementation
    }
}
