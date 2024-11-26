import com.AngryBirds.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static org.junit.Assert.*;


public class Test {

    @org.junit.Test
    public void testPigDestruction(){

        World world = new World(new Vector2(0, -9.8f), true);

        for (int i = 0; i < 60; i++) {
            world.step(1 / 60f,6,2);
        }

        Pig pig = new Pig(world, 10, 0, 2);
        Bird bird = new Bird(world, 10, 0, 2);
        pig.healthReduce(bird.getStrength());

        assertTrue(pig.isMarkedForDestruction());





    }

    @org.junit.Test
    public void testBlockDestruction(){

        World world = new World(new Vector2(0, -9.8f), true);

        for (int i = 0; i < 60; i++) {
            world.step(1 / 60f,6,2);
        }

        Block block = new Block(world, 10, 0, 106, 106, 2);
        Bird bird = new Bird(world, 10, 0, 2);
        block.healthReduce(bird.getStrength());

        assertTrue(block.isMarkedForDestruction());





    }

    @org.junit.Test
    public void testObjectCreation(){

        World world = new World(new Vector2(0, -9.8f), true);

        for (int i = 0; i < 60; i++) {
            world.step(1 / 60f,6,2);
        }

        Block block = new Block(world, 10, 0, 106, 106, 2);
        Bird bird = new Bird(world, 10, 0, 2);
        Pig pig = new Pig(world, 10, 0, 2);

        assertNotNull(bird.getBody());
        assertNotNull(block.getBody());
        assertNotNull(pig.getBody());


    }

    @org.junit.Test
    public void testPositionUpdate(){

            World world = new World(new Vector2(0, -9.8f), true);

            for (int i = 0; i < 60; i++) {
                world.step(1 / 60f,6,2);
            }

            Block block = new Block(world, 10, 0, 106, 106, 2);
            Bird bird = new Bird(world, 10, 0, 2);
            Pig pig = new Pig(world, 10, 0, 2);

            block.setPosition(20, 20);
            bird.updatePosition();
            pig.setPosition(20, 20);

            assertEquals(20, block.getX(), 0.1);
            assertEquals(20, block.getY(), 0.1);
            assertEquals(400/100f, bird.getBody().getPosition().x, 0.1);
            assertEquals(450/100f, bird.getBody().getPosition().y, 0.1);
            assertEquals(20, pig.getX(), 0.1);
            assertEquals(20, pig.getY(), 0.1);
    }

    @org.junit.Test
    public void testBodyDestruction(){
        World world = new World(new Vector2(0, -9.8f), true);

        for (int i = 0; i < 60; i++) {
            world.step(1 / 60f,6,2);
        }

        Block block = new Block(world, 10, 0, 106, 106, 2);
        block.safelyDestroy(world);

        //Will not be destroyed as not marked for destrucction
        assertNotNull(block.getBody());

        block.markForDestruction();

        //Will not be destroyed
        block.safelyDestroy(world);
        assertNull(block.getBody());
    }



}
