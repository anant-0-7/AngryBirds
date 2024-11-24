package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LevelPage extends ScreenAdapter {
    private Skin skin;
    private Stage stage;
    private Texture backgroundTexture;
    private Texture headingTexture;
    private MainTop game;

    public LevelPage(MainTop game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture("background2.jpg");
        headingTexture = new Texture("level_selection.png");

        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        ImageButton levelOne = createLevelButton("l1B.png");
        ImageButton levelTwo = createLevelButton("l2B.png");
        ImageButton levelThree = createLevelButton("l3B.png");
        ImageButton levelFour = createLevelButton("lockB.png");
        ImageButton levelFive = createLevelButton("lockB.png");

        table.padTop(150);
        table.add(levelOne).size(200,200).padRight(30);
        table.add(levelTwo).size(200,200).padRight(30);
        table.add(levelThree).size(200,200).padRight(30);
        table.add(levelFour).size(200,200).padRight(30);
        table.add(levelFive).size(200,200);
        Image headingImage = new Image(headingTexture);
        headingImage.setSize(headingTexture.getWidth() * 1.8f, headingTexture.getHeight() * 1.8f);
        headingImage.setPosition((stage.getWidth() - headingImage.getWidth()) / 2, stage.getHeight() - headingImage.getHeight() - 50);

        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        backgroundImage.setPosition(0, 0);
        levelOne.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, null));
            }
        });
        levelTwo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen2(game, null));
            }
        });

        levelThree.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen3(game, null));
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(headingImage);
        stage.addActor(table);
    }

    private ImageButton createLevelButton(String texturePath) {
        Texture levelTexture = new Texture(Gdx.files.internal(texturePath));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(levelTexture));
        ImageButton button = new ImageButton(drawable);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        return button;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        headingTexture.dispose();
        stage.dispose();
        skin.dispose();
    }
}
