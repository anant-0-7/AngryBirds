package com.AngryBirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoseGame extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Texture image;
    private Texture pimage;
    private MainTop game;

    public LoseGame(MainTop game) {
        this.game = game;
    }

    @Override
    public void show() {
        image = new Texture("lose_background.png");
        pimage = new Texture("Level-Failed.png");
        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table=new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0,Gdx.graphics.getHeight());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        ImageButton resButton = createLevelButton("rB.png");
        ImageButton quitButton = createLevelButton("QuitGame.png");
        Image img = new Image(image);
        Image pimg = new Image(pimage);
        table.padTop(300);
        table.add(resButton).size(350,200).padBottom(30);
        table.row();
        table.add(quitButton).size(300,200);


        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        resButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        img.setPosition(0, 0);
        pimg.setPosition(stage.getWidth()/2-200, stage.getHeight()-200);
        pimg.setSize(stage.getWidth()/3, stage.getHeight()/5);
        stage.addActor(img);
        stage.addActor(pimg);
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
    }


    @Override
    public void dispose() {
        image.dispose();
        stage.dispose();
    }
}
