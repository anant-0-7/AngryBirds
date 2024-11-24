package com.AngryBirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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

public class GameScreen2 extends ScreenAdapter {
    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch spriteBatch;

    private Texture backgroundTexture;
    private Texture pauseButtonTexture, backButtonTexture;
    private RedBird redBird;
    private YellowBird yellowBird;
    private YellowBird yellowBird2;
    private WoodSquare woodSquare;
    private GlassSquare glassSquare;
    private GlassSquare glassSquare1;
    private Pig pig;
    private Pig pig1;
    private MainTop game;
    private Slingshot slingshot;
    private StoneSquare stoneSquare;
    ArrayList<Bird> birds;
    int curr=0;
    SaveGame2 saveGame;

    public GameScreen2(MainTop game, SaveGame2 saveGame) {
        this.game = game;
        if(saveGame ==null)curr =0;
        else curr=saveGame.getCurr();
        birds=new ArrayList<Bird>();
        this.saveGame=saveGame;

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
                game.setScreen(new PausePage(game, new SaveGame2(redBird, yellowBird, yellowBird2, woodSquare, glassSquare, glassSquare1, pig, pig1, stoneSquare, curr)));
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

        if(saveGame == null){
            redBird = new RedBird(world, 150, 300);
            redBird.updatePosition();
            yellowBird = new YellowBird(world, 100, 300);
            yellowBird2 = new YellowBird(world, 50, 300);
            birds.add(redBird);
            birds.add(yellowBird);
            birds.add(yellowBird2);

            woodSquare = new WoodSquare(world, 1385, 275);
            glassSquare = new GlassSquare(world, 1275, 275);
            glassSquare1 = new GlassSquare(world, 1385, 375);
            stoneSquare = new StoneSquare(world, 1275, 375);

            pig = new PanPig(world, 1385, 465);
            pig1 = new NormalPig(world, 1275, 465);

        }

        else{
            redBird = saveGame.getRedBird();
            yellowBird = saveGame.getYellowBird();
            yellowBird2 = saveGame.getYellowBird2();
            woodSquare = saveGame.getWoodSquare();
            glassSquare = saveGame.getGlassSquare();
            glassSquare1 = saveGame.getGlassSquare1();
            stoneSquare = saveGame.getStoneSquare();
            pig = saveGame.getPig();
            pig1 = saveGame.getPig1();
            birds.add(redBird);
            birds.add(yellowBird);
            birds.add(yellowBird2);
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

                } else {
                    birds.get(curr).update();
                    birds.get(curr).render(spriteBatch);
                }
            }
        }
        else{
            if(pig.getBody()!=null){
                game.setScreen(new LoseGame(game,2));
            }
        }

        if(pig.getBody()==null){
            game.setScreen(new GameScreen3(game, null));
        }

        if(woodSquare.getBody()!=null) {

            if (woodSquare.isMarkedDestructed) {
                if(glassSquare1.getBody()!=null) {
                    if(pig.getBody()!=null) {
                        pig.setPosition(glassSquare1.getBody().getPosition().x * 100, glassSquare1.getBody().getPosition().y * 100);
                        pig.healthReduce(1);
                    }
                    glassSquare1.setPosition(woodSquare.getBody().getPosition().x * 100, woodSquare.getBody().getPosition().y * 100);
                    glassSquare1.healthReduce(1);
                }

                else {
                    if(pig.getBody()!=null) {
                        pig.setPosition(woodSquare.getBody().getPosition().x * 100, woodSquare.getBody().getPosition().y * 100);
                        pig.healthReduce(1);
                    }
                }
                woodSquare.safelyDestroy(world);

                woodSquare.dispose();

            }
            else {
                woodSquare.render(spriteBatch);
            }
        }

        if(glassSquare1.getBody()!=null) {

            if (glassSquare1.isMarkedDestructed) {
                if(pig.getBody()!=null) {
                    pig.setPosition(glassSquare1.getBody().getPosition().x * 100, glassSquare1.getBody().getPosition().y * 100);
                    pig.healthReduce(1);
                }
                glassSquare1.safelyDestroy(world);

                glassSquare1.dispose();

            }
            else {
                glassSquare1.render(spriteBatch);
            }
        }

        if(glassSquare.getBody()!=null) {

            if (glassSquare.isMarkedDestructed) {
                if(stoneSquare.getBody()!=null) {

                    if(pig1.getBody()!=null) {
                        pig1.setPosition(stoneSquare.getBody().getPosition().x * 100, stoneSquare.getBody().getPosition().y * 100);
                        pig1.healthReduce(1);
                    }

                    stoneSquare.healthReduce(1);
                    stoneSquare.setPosition(glassSquare.getBody().getPosition().x * 100, glassSquare.getBody().getPosition().y * 100);
                }
                else{
                    if(pig1.getBody()!=null) {
                        pig1.setPosition(glassSquare.getBody().getPosition().x * 100, glassSquare.getBody().getPosition().y * 100);
                        pig1.healthReduce(1);
                    }
                }
                glassSquare.safelyDestroy(world);

                glassSquare.dispose();

            }
            else {
                glassSquare.render(spriteBatch);
            }
        }

        if(stoneSquare.getBody()!=null) {

            if (stoneSquare.isMarkedDestructed) {

                if(pig1.getBody()!=null) {
                    pig1.setPosition(stoneSquare.getBody().getPosition().x * 100, stoneSquare.getBody().getPosition().y * 100);
                    pig1.healthReduce(1);
                }
                stoneSquare.safelyDestroy(world);
                stoneSquare.dispose();

            }
            else {
                stoneSquare.render(spriteBatch);
            }
        }

        if(pig.getBody()!=null) {

            if (pig.isMarkedDestructed) {
                pig.safelyDestroy(world);
                pig.dispose();

            }
            else {
                pig.render(spriteBatch);
            }
        }
        if(pig1.getBody()!=null) {

            if (pig1.isMarkedDestructed) {
                pig1.safelyDestroy(world);
                pig1.dispose();

            }
            else {
                pig1.render(spriteBatch);
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
