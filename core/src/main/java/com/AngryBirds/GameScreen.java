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

import java.util.ArrayList;

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
    private WoodSquare woodSquare;
    private GlassSquare glassSquare;
    private Pig pig;
    private MainTop game;
    private Slingshot slingshot;
    private StoneSquare stoneSquare;
    ArrayList<Bird> birds;
    SaveGame1 saveGame;
    int curr=0;

    public GameScreen(MainTop game, SaveGame1 saveGame) {
        this.game = game;
        this.saveGame=saveGame;
        if(saveGame ==null)curr =0;
        else curr=saveGame.getCurr();
        birds=new ArrayList<Bird>();
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
                game.setScreen(new PausePage(game, new SaveGame1(redBird, redBird2, redBird3, woodSquare, glassSquare, pig, stoneSquare, curr)));
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
        if(saveGame==null){
            redBird = new RedBird(world, 150, 300);
            redBird.updatePosition();
            redBird2 = new RedBird(world, 100, 300);
            redBird3 = new RedBird(world, 50, 300);
            birds.add(redBird);
            birds.add(redBird2);
            birds.add(redBird3);

            woodSquare = new WoodSquare(world, 1385, 275);
            glassSquare = new GlassSquare(world, 1275, 275);
            stoneSquare = new StoneSquare(world, 1275, 375);

            pig = new KingPig(world, 1385, 365);

        }

        else{
            redBird = saveGame.getRedBird();
            redBird2 = saveGame.getRedBird2();
            redBird3 = saveGame.getRedBird3();
//            if(redBird.getBody()!=null)redBird.resetState();
//            if(redBird2.getBody()!=null)redBird2.resetState();
//            if(redBird3.getBody()!=null)redBird3.resetState();

            woodSquare = saveGame.getWoodSquare();
            glassSquare = saveGame.getGlassSquare();
            pig = saveGame.getPig();
            stoneSquare = saveGame.getStoneSquare();
            birds.add(redBird);
            birds.add(redBird2);
            birds.add(redBird3);
        }



    }

    private void createStaticBody(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x / 100f, y / 100f);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 200f, height / 200f);

        body.createFixture(shape, 0.0f);
        shape.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 6, 2);

        // Render background
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, 1920, 1080);
        spriteBatch.end();

        if(curr<birds.size()){

            int x = curr+1;
            while(x<birds.size()){
                birds.get(x).render(spriteBatch);
                x++;
            }

            if(birds.get(curr).getBody()!=null) {

                if (birds.get(curr).isMarkedDestructed) {
                    birds.get(curr).safelyDestroy(world);
                    birds.get(curr).dispose();
                    curr++;
                    if(curr<birds.size()){
                        birds.get(curr).updatePosition();
                    }

                }
                else {
                    birds.get(curr).update();
                    birds.get(curr).render(spriteBatch);
                }
            }
        }
        else{
            if(pig.getBody()!=null){
                game.setScreen(new LoseGame(game,1));
            }
        }

        if(pig.getBody()==null){
            game.setScreen(new GameScreen2(game, null));
        }
//        if(redBird.getBody()!=null) {
//
//            if (redBird.isMarkedDestructed) {
//                redBird.safelyDestroy(world);
//                redBird.dispose();
//
//            } else {
//                redBird.updatePosition1();
//                redBird.render(spriteBatch);
//            }
//        }

        if(woodSquare.getBody()!=null) {

            if (woodSquare.isMarkedDestructed) {
                if(pig.getBody()!=null) {
                    pig.setPosition(woodSquare.getBody().getPosition().x * 100, woodSquare.getBody().getPosition().y * 100);
                    pig.healthReduce(1);
                }
                woodSquare.safelyDestroy(world);

                woodSquare.dispose();

            } else {
                woodSquare.render(spriteBatch);
            }
        }
        if(glassSquare.getBody()!=null) {

            if (glassSquare.isMarkedDestructed) {
                if(stoneSquare.getBody()!=null) {
                    stoneSquare.healthReduce(1);
                    stoneSquare.setPosition(glassSquare.getBody().getPosition().x * 100, glassSquare.getBody().getPosition().y * 100);
                }
                glassSquare.safelyDestroy(world);

                glassSquare.dispose();

            } else {
                glassSquare.render(spriteBatch);
            }
        }

        if(stoneSquare.getBody()!=null) {

            if (stoneSquare.isMarkedDestructed) {
                stoneSquare.safelyDestroy(world);
                stoneSquare.dispose();

            } else {
                stoneSquare.render(spriteBatch);
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
