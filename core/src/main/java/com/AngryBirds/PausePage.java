package com.AngryBirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PausePage extends ScreenAdapter {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Texture image;
    private Texture pimage;
    private MainTop game;

    public PausePage(MainTop game) {
        this.game = game;
    }

    @Override
    public void show() {
        image = new Texture("background2.jpg");
        pimage = new Texture("pimage.png");
        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table=new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0,Gdx.graphics.getHeight());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextButton resumeButton =new TextButton("Resume",skin,"default");
        final TextButton newButton =new TextButton("Save Game",skin,"default");
        final TextButton resButton =new TextButton("Restart",skin,"default");
        final TextButton quitButton =new TextButton("Quit Game",skin,"default");
        Image img = new Image(image);
        Image pimg = new Image(pimage);
        table.padTop(100);
        table.add(resumeButton).padBottom(30);
        table.row();
        table.add(newButton).padBottom(30);
        table.row();
        table.add(resButton).padBottom(30);
        table.row();
        table.add(quitButton);

        img.setPosition(0, 0);
        pimg.setPosition(stage.getWidth()/2-100, stage.getHeight()-100);
        pimg.setSize(stage.getWidth()/3, stage.getHeight()/5);

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
        stage.addActor(img);
        stage.addActor(pimg);
        stage.addActor(table);



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
