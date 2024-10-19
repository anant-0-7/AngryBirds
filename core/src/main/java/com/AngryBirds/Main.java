package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ScreenAdapter{


    private Stage stage;
    private Skin skin;
    private Table table;
    private Texture image;
    private MainTop game;
    private boolean next;

    public Main(MainTop game){
        this.game=game;
    }

    @Override
    public void show() {
        image = new Texture("angryBirds1.jpg");
        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table=new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextButton newButton =new TextButton("New Game",skin,"default");
        final TextButton loadButton =new TextButton("Load Game",skin,"default");
        final TextButton quitButton =new TextButton("Quit Game",skin,"default");

        newButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                next=true;
            }
        });
        Image img = new Image(image);
        table.padTop(50);
        table.add(newButton).padBottom(30);
        table.row();
        table.add(loadButton).padBottom(30);
        table.row();
        table.add(quitButton);

        img.setPosition(0, 0);

        stage.addActor(img);
        stage.addActor(table);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if(next){
            game.setScreen(new LevelPage(game));
        }

    }


    @Override
    public void dispose() {
        image.dispose();
        stage.dispose();
    }
}
