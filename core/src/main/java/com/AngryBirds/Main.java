package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

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
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        final TextButton newButton = new TextButton("New Game", skin, "default");
        newButton.getLabel().setFontScale(1.5f);
        newButton.pad(20);

        final TextButton loadButton = new TextButton("Load Game", skin, "default");
        loadButton.getLabel().setFontScale(1.5f);
        loadButton.pad(20);

        final TextButton quitButton = new TextButton("Quit Game", skin, "default");
        quitButton.getLabel().setFontScale(1.5f);
        quitButton.pad(20);

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

        Image img = new Image(image);
        img.setSize(stage.getWidth(), stage.getHeight());
        img.setPosition(0, 0);

        Texture headingTexture = new Texture("angry-birds-logo.png");
        Image headingImage = new Image(headingTexture);
        headingImage.setSize(headingTexture.getWidth() * 0.5f, headingTexture.getHeight() * 0.5f);
        headingImage.setPosition((stage.getWidth() - headingImage.getWidth()) / 2, stage.getHeight() - headingImage.getHeight() - 20);

        table.padTop(50);
        table.add(newButton).padBottom(30);
        table.row();
        table.add(loadButton).padBottom(30);
        table.row();
        table.add(quitButton);

        stage.addActor(img);
        stage.addActor(headingImage);
        stage.addActor(table);
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
