package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter {
    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch spriteBatch;

    private Texture backgroundTexture;
    private Texture pauseButtonTexture, backButtonTexture;
    private RedBird redBird;
    private RedBird redBird2;
    private RedBird redBird3;
    private WoodRod woodRod;
    private WoodSquare woodSquare;
    private GlassSquare glassSquare;
    private Pig pig;
    private MainTop game;
    private Slingshot slingshot;

    public GameScreen(MainTop game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);
        ContactListner c=new ContactListner();
        c.world=world;
        world.setContactListener(c);
        debugRenderer = new Box2DDebugRenderer();
        spriteBatch = new SpriteBatch();

        backgroundTexture = new Texture("gameBackground.jpg");
        pauseButtonTexture = new Texture("pauseButton.png");
        backButtonTexture = new Texture("bB.png");

        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);

        createButtons();

        createGameObjects();
    }

    private void createButtons() {
        ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(pauseButtonTexture));
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backButtonTexture));

        pauseButton.setSize(150, 150);
        backButton.setSize(150, 150);
        pauseButton.setPosition(1700, 900);
        backButton.setPosition(100, 900);

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

        stage.addActor(pauseButton);
        stage.addActor(backButton);
    }

    private void createGameObjects() {
        createStaticBody(0, 220, 10000, 10);
        createStaticBody(400,350 , 5, 0);

        slingshot = new Slingshot(275, 235);
        redBird = new RedBird(world, 200, 300);
        redBird2 = new RedBird(world, 150, 300);
        redBird3 = new RedBird(world, 100, 300);
        redBird.updatePosition();
        Texture t=new Texture("woodRod.png");

        woodRod = new WoodRod(world, 1325, 235,t);
        woodSquare = new WoodSquare(world, 1385, 295);
        glassSquare = new GlassSquare(world, 1275, 295);
        pig = new KingPig(world, 1330, 385);


    }

    private void createStaticBody(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x / 100f, y / 100f); // Convert to meters
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 200f, height / 200f); // Convert to meters

        body.createFixture(shape, 0.0f);
        shape.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Step the physics simulation
        world.step(1 / 60f, 6, 2);

        // Render background
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, 1920, 1080);
        spriteBatch.end();
        if(redBird.getBody()!=null) {

            if (redBird.isMarkedDestructed) {
                redBird.safelyDestroy(world);
                redBird.dispose();

            } else {
                redBird.update();
                redBird.render(spriteBatch);
            }
        }
        if(pig.getBody()!=null) {

            if (pig.isMarkedDestructed) {
                pig.safelyDestroy(world);
                pig.dispose();

            } else {
                pig.render(spriteBatch);
            }
        }
        if(woodRod.getBody()!=null) {

            if (woodRod.isMarkedDestructed) {
                woodRod.safelyDestroy(world);
                woodRod.dispose();

            } else {
                woodRod.render(spriteBatch);
            }
        }
        if(woodSquare.getBody()!=null) {

            if (woodSquare.isMarkedDestructed) {
                woodSquare.safelyDestroy(world);
                woodSquare.dispose();

            } else {
                woodSquare.render(spriteBatch);
            }
        }
        if(glassSquare.getBody()!=null) {

            if (glassSquare.isMarkedDestructed) {
                glassSquare.safelyDestroy(world);
                glassSquare.dispose();

            } else {
                glassSquare.render(spriteBatch);
            }
        }

        // Render game objects
        slingshot.render(spriteBatch);





        stage.act();
        stage.draw();
        debugRenderer.render(world, stage.getCamera().combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        spriteBatch.dispose();
        backgroundTexture.dispose();
        pauseButtonTexture.dispose();
        backButtonTexture.dispose();
        stage.dispose();
    }
}
