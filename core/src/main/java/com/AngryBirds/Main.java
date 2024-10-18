package com.AngryBirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Texture image;

    @Override
    public void create() {
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
    public void render() {
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
