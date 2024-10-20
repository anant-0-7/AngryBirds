package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ApplicationAdapter {

    private Stage stage;
    private Texture image;
    private Texture redBirdTexture;
    private Texture yellowBirdTexture;
    private Texture blackBirdTexture;
    private Texture slingShotTexture;
    private Texture WoodRod;
    private Texture WoodSquare;
    private Texture GlassSquare;
    private Texture pig;
    private Texture pauseButtonTexture;

    @Override
    public void create() {
        // Load textures
        image = new Texture("gameBackground.jpg");
        redBirdTexture = new Texture("redbird.png");
        yellowBirdTexture = new Texture("yellowbird.png");
        blackBirdTexture = new Texture("blackbird.png");
        slingShotTexture = new Texture("slingshot.png");

        pauseButtonTexture = new Texture("pauseButton.png");
        Texture headingTexture = new Texture("angry-birds-logo.png");

        WoodRod = new Texture("woodRod.png");
        WoodSquare = new Texture("woodSquare.png");
        GlassSquare = new Texture("glassSquare.png");
        pig = new Texture("kingPig.png");

        // Create stage and set up input processor
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);

        // Create Image actors
        Image background = new Image(image);
        Image redBird = new Image(redBirdTexture);
        Image yellowBird = new Image(yellowBirdTexture);
        Image blackBird = new Image(blackBirdTexture);
        Image slingShot = new Image(slingShotTexture);
        Image headingImage = new Image(headingTexture);
        Image wr = new Image(WoodRod);
        Image ws = new Image(WoodSquare);
        Image gs = new Image(GlassSquare);
        Image p = new Image(pig);

        // Create TextureRegionDrawable for ImageButton (pause button)
        TextureRegionDrawable pauseButtonDrawable = new TextureRegionDrawable(pauseButtonTexture);
        ImageButton pauseButton = new ImageButton(pauseButtonDrawable);

        headingImage.setSize(headingTexture.getWidth() * 0.5f, headingTexture.getHeight() * 0.5f);
        headingImage.setPosition((stage.getWidth() - headingImage.getWidth()) / 2, stage.getHeight() - headingImage.getHeight() - 20);

        // Set pause button size and position next to the heading on the right side
        pauseButton.setSize(200, 200); // Adjust the size of the pause button
        pauseButton.setPosition(headingImage.getX() + headingImage.getWidth() + 20, headingImage.getY() + (headingImage.getHeight() - pauseButton.getHeight()) / 2); // Position it beside the heading

        // Set background size and position
        background.setSize(stage.getWidth(), stage.getHeight());
        background.setPosition(0, 0);

        // Set bird sizes and positions in the x direction (birds come first)
        redBird.setSize(100, 100);
        yellowBird.setSize(100, 100);
        blackBird.setSize(100, 100);

        redBird.setPosition(80, 230); // Place the red bird first
        yellowBird.setPosition(170, 230); // Place the yellow bird next
        blackBird.setPosition(270, 230); // Place the black bird next

        // Set slingshot size and position after the birds
        slingShot.setSize(200, 250);
        slingShot.setPosition(350, 230); // Slingshot after the birds

        // Position blocks and the pig on the right side
        // Set WoodRod (horizontally below)
        wr.setSize(200, 20); // Adjust size to make it a horizontal rod
        wr.setPosition(1325, 225); // Right side, lower part

        // Set WoodSquare above the WoodRod
        ws.setSize(100, 100); // Adjust size
        ws.setPosition(1330, 245); // Above WoodRod

        // Set GlassSquare next to the WoodSquare
        gs.setSize(100, 100);
        gs.setPosition(1430, 245); // Next to the WoodSquare

        // Set King Pig above the blocks
        p.setSize(80, 80); // Adjust size for the pig
        p.setPosition(1380, 345); // Above WoodSquare and GlassSquare

        // Add actors to the stage
        stage.addActor(background);
        stage.addActor(redBird);
        stage.addActor(yellowBird);
        stage.addActor(blackBird);
        stage.addActor(slingShot);
        stage.addActor(headingImage);
        stage.addActor(pauseButton); // Add the pause button
        stage.addActor(wr); // WoodRod
        stage.addActor(ws); // WoodSquare
        stage.addActor(gs); // GlassSquare
        stage.addActor(p);  // King Pig

        // Add event listener to handle the pause button click

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        image.dispose();
        redBirdTexture.dispose();
        yellowBirdTexture.dispose();
        blackBirdTexture.dispose();
        slingShotTexture.dispose();
        WoodRod.dispose();
        WoodSquare.dispose();
        GlassSquare.dispose();
        pig.dispose();
        pauseButtonTexture.dispose();
        stage.dispose();
    }
}
