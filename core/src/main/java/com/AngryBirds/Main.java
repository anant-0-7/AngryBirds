package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ScreenAdapter {

    private Stage stage;
    private Texture image;
    private final MainTop game;
    private boolean next;

    public Main(MainTop game) {
        this.game = game;
    }

    @Override
    public void show() {
        image = new Texture("background2.jpg");
        stage = new Stage(new FitViewport(1600,900));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        ImageButton newButton = createLevelButton("playGame.png");

        ImageButton loadButton = createLevelButton("LoadGame.png");

        ImageButton quitButton = createLevelButton("QuitGame.png");

        newButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                next = true;
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        loadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                File file = new File("GameData.txt");

                if (file.exists()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        SaveGame sgame = (SaveGame) ois.readObject();
                        int level=sgame.getLevel();
                        if(level==1){
                            game.setScreen(new GameScreen(game,(SaveGame1) sgame));
                        } else if (level==2) {
                            game.setScreen(new GameScreen2(game,(SaveGame2) sgame));
                        }
                        else{
                            game.setScreen(new GameScreen3(game,(SaveGame3) sgame));
                        }


                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException("Failed to initialize game from file", e);
                    }
                }
                else {
                    game.setScreen(new GameScreen(game,null));
                }
            }
        });

        Image img = new Image(image);
        img.setSize(stage.getWidth(), stage.getHeight());
        img.setPosition(0, 0);

        Texture headingTexture = new Texture("angry-birds-logo.png");
        Image headingImage = new Image(headingTexture);
        headingImage.setSize(headingTexture.getWidth() * 0.5f, headingTexture.getHeight() * 0.5f);
        headingImage.setPosition((stage.getWidth() - headingImage.getWidth()) / 2, stage.getHeight() - headingImage.getHeight() - 20);

        table.padTop(250);
        table.add(newButton).size(500,300);
        table.row();
        table.add(loadButton).size(300,150);
        table.row();
        table.add(quitButton).size(300,150);

        stage.addActor(img);
        stage.addActor(headingImage);
        stage.addActor(table);
    }
    private ImageButton createLevelButton(String texturePath) {
        Texture levelTexture = new Texture(Gdx.files.internal(texturePath));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(levelTexture));
        ImageButton button = new ImageButton(drawable);
        return button;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (next) {
            game.setScreen(new LevelPage(game));
        }
    }

    @Override
    public void dispose() {
        image.dispose();
        stage.dispose();
    }
}
