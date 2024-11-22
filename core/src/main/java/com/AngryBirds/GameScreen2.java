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

public class GameScreen2 extends ScreenAdapter {
    private Stage stage;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch spriteBatch;

    private Texture backgroundTexture;
    private Texture pauseButtonTexture, backButtonTexture;
    private RedBird redBird;
    private YellowBird yellowBird;
    private BlackBird blackBird;
    private WoodRod woodRod;
    private WoodSquare woodSquare1;
    private WoodSquare woodSquare2;
    private GlassSquare glassSquare;
    private Pig kingPig;
    private Pig panPig;
    private Pig normPig;
    private MainTop game;
    private Slingshot slingshot;
    private StoneSquare stoneSquare;
    ArrayList<Bird> birds;
    int curr=0;

    public GameScreen2(MainTop game) {
        this.game = game;
        curr =0;
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
                game.setScreen(new PausePage(game,2));
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
        redBird = new RedBird(world, 150, 300);
        redBird.updatePosition();
        yellowBird = new YellowBird(world, 100, 300);
        blackBird = new BlackBird(world, 50, 300);
        birds.add(redBird);
        birds.add(yellowBird);
        birds.add(blackBird);

        //Texture t=new Texture("woodRod.png");

//        woodRod = new WoodRod(world, 1325, 235,t);
        woodSquare1 = new WoodSquare(world, 1400, 275);
        woodSquare2 = new WoodSquare(world, 1150, 275);
        glassSquare = new GlassSquare(world, 1275, 275);
        stoneSquare = new StoneSquare(world, 1275, 375);

        kingPig = new KingPig(world, 1400, 365);
        panPig=new PanPig(world, 1275, 465);
        normPig=new NormalPig(world, 1150, 365);



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
            if(kingPig.getBody()!=null || panPig.getBody()!=null || normPig.getBody()!=null){
                game.setScreen(new LoseGame(game,2));
            }

        }
        if(kingPig.getBody()==null && panPig.getBody()==null && normPig.getBody()==null){
            game.setScreen(new GameScreen2(game));
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

//        if(woodRod.getBody()!=null) {
//
//            if (woodRod.isMarkedDestructed) {
//                woodRod.safelyDestroy(world);
//                woodRod.dispose();
//
//            } else {
//                woodRod.render(spriteBatch);
//            }
//        }
        if(woodSquare1.getBody()!=null) {

            if (woodSquare1.isMarkedDestructed) {
                if(kingPig.getBody()!=null) {
                    kingPig.setPosition(woodSquare1.getBody().getPosition().x * 100, woodSquare1.getBody().getPosition().y * 100);
                    kingPig.healthReduce(1);
                }
                woodSquare1.safelyDestroy(world);

                woodSquare1.dispose();

            } else {
                woodSquare1.render(spriteBatch);
            }
        }
        if(woodSquare2.getBody()!=null) {

            if (woodSquare2.isMarkedDestructed) {
                if(normPig.getBody()!=null) {
                    normPig.setPosition(woodSquare2.getBody().getPosition().x * 100, woodSquare2.getBody().getPosition().y * 100);
                    normPig.healthReduce(1);
                }
                woodSquare2.safelyDestroy(world);
                woodSquare2.dispose();

            } else {
                woodSquare2.render(spriteBatch);
            }
        }
        if(glassSquare.getBody()!=null) {

            if (glassSquare.isMarkedDestructed) {
                if(panPig.getBody()!=null){
                    panPig.setPosition(stoneSquare.getBody().getPosition().x*100,stoneSquare.getBody().getPosition().y*100-15);
                }
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
                if(panPig.getBody()!=null) {
                    panPig.setPosition(stoneSquare.getBody().getPosition().x * 100, stoneSquare.getBody().getPosition().y * 100-15);
                    panPig.healthReduce(1);
                }
                stoneSquare.safelyDestroy(world);

                stoneSquare.dispose();

            } else {
                stoneSquare.render(spriteBatch);
            }
        }
        if(kingPig.getBody()!=null) {

            if (kingPig.isMarkedDestructed) {
                kingPig.safelyDestroy(world);
                kingPig.dispose();

            } else {
                kingPig.render(spriteBatch);
            }
        }
        if(normPig.getBody()!=null) {

            if (normPig.isMarkedDestructed) {
                normPig.safelyDestroy(world);
                normPig.dispose();

            } else {
                normPig.render(spriteBatch);
            }
        }
        if(panPig.getBody()!=null) {

            if (panPig.isMarkedDestructed) {
                panPig.safelyDestroy(world);
                panPig.dispose();

            } else {
                panPig.render(spriteBatch);
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