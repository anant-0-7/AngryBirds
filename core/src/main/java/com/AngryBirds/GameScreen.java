package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter {

    private Stage stage;
    private Texture image;
    private RedBird redBirdTexture;
    private YellowBird yellowBirdTexture;
    private BlackBird blackBirdTexture;
    private Slingshot slingShotTexture;
    private WoodRod WoodRod;
    private WoodSquare WoodSquare;
    private GlassSquare GlassSquare;
    private Pig pig;
    private Texture pauseButtonTexture;
    private Texture backButtonTexture;
    private MainTop game;

    public GameScreen(MainTop game) {
        this.game = game;
    }

    @Override
    public void show() {
        image = new Texture("gameBackground.jpg");
        redBirdTexture = new RedBird(80, 230);
        yellowBirdTexture = new YellowBird(170, 230);
        blackBirdTexture = new BlackBird(270, 230);
        slingShotTexture = new Slingshot(350, 230);

        pauseButtonTexture = new Texture("pauseButton.png");
        backButtonTexture = new Texture("bB.png");
        Texture headingTexture = new Texture("angry-birds-logo.png");

        WoodRod = new WoodRod(1325, 225);
        WoodSquare = new WoodSquare(1330, 245);
        GlassSquare = new GlassSquare(1430, 245);
        pig = new KingPig(1380, 345);

        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);

        Image background = new Image(image);
        Image redBird = redBirdTexture.getImage();
        Image yellowBird = yellowBirdTexture.getImage();
        Image blackBird = blackBirdTexture.getImage();

        Image slingShot = slingShotTexture.getImage();
        Image headingImage = new Image(headingTexture);
        Image wr = WoodRod.getImage();
        Image ws = WoodSquare.getImage();
        Image gs = GlassSquare.getImage();
        Image p = pig.getImage();

        TextureRegionDrawable pauseButtonDrawable = new TextureRegionDrawable(pauseButtonTexture);
        ImageButton pauseButton = new ImageButton(pauseButtonDrawable);
        TextureRegionDrawable backButtonDrawable = new TextureRegionDrawable(backButtonTexture);
        ImageButton backButton = new ImageButton(backButtonDrawable);

        headingImage.setSize(headingTexture.getWidth() * 0.5f, headingTexture.getHeight() * 0.5f);
        headingImage.setPosition((stage.getWidth() - headingImage.getWidth()) / 2, stage.getHeight() - headingImage.getHeight() - 20);

        backButton.setSize(150, 150);
        pauseButton.setSize(150, 150);

        backButton.setPosition(headingImage.getX() - backButton.getWidth() - 20, headingImage.getY() + (headingImage.getHeight() - backButton.getHeight()) / 2);

        pauseButton.setPosition(headingImage.getX() + headingImage.getWidth() + 20, headingImage.getY() + (headingImage.getHeight() - pauseButton.getHeight()) / 2);
        background.setSize(stage.getWidth(), stage.getHeight());
        background.setPosition(0, 0);

        redBird.setSize(100, 100);
        yellowBird.setSize(100, 100);
        blackBird.setSize(100, 100);

        redBird.setPosition(redBirdTexture.getX(), redBirdTexture.getY());
        yellowBird.setPosition(yellowBirdTexture.getX(), yellowBirdTexture.getY());
        blackBird.setPosition(blackBirdTexture.getX(), blackBirdTexture.getY());

        slingShot.setSize(200, 250);
        slingShot.setPosition(slingShotTexture.getX(), slingShotTexture.getY());

        wr.setSize(200, 20);
        wr.setPosition(WoodRod.getX(), WoodRod.getY());

        ws.setSize(100, 100);
        ws.setPosition(WoodSquare.getX(), WoodSquare.getY());

        gs.setSize(100, 100);
        gs.setPosition(GlassSquare.getX(), GlassSquare.getY());

        p.setSize(80, 80);
        p.setPosition(pig.getX(), pig.getY());

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PausePage(game));
            }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Main(game));
            }
        });
        redBird.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoseGame(game));
            }
        });

        stage.addActor(background);
        stage.addActor(redBird);
        stage.addActor(yellowBird);
        stage.addActor(blackBird);
        stage.addActor(slingShot);
        stage.addActor(headingImage);
        stage.addActor(pauseButton);
        stage.addActor(backButton);
        stage.addActor(wr);
        stage.addActor(ws);
        stage.addActor(gs);
        stage.addActor(p);


    }

    @Override
    public void render(float delta) {
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
