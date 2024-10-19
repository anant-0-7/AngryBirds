package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelPage extends ScreenAdapter{
    private Skin skin;
    private Stage stage;
    private Table table;
    private Texture image;
    private Texture pimage;
    private MainTop game;


    public LevelPage(MainTop game){
        this.game=game;
    }
    @Override
    public void show() {
        image = new Texture("angryBirds1.jpg");
        pimage = new Texture("limage.png");
        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table=new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.left);
        table.setPosition(0,Gdx.graphics.getHeight());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Texture t1=new Texture(Gdx.files.internal("level1.png"));
        Drawable drawable=new TextureRegionDrawable(new TextureRegion(t1));
        ImageButton levelOne =new ImageButton(drawable);
        Texture t2=new Texture(Gdx.files.internal("level2.png"));
        Drawable drawable2=new TextureRegionDrawable(new TextureRegion(t2));
        ImageButton levelTwo =new ImageButton(drawable2);
        Texture t3=new Texture(Gdx.files.internal("level3.png"));
        Drawable drawable3=new TextureRegionDrawable(new TextureRegion(t3));
        ImageButton levelThree =new ImageButton(drawable3);
        Image img = new Image(image);
        Image pimg = new Image(pimage);
        levelOne.setHeight(50);
        levelOne.setWidth(50);
        table.padTop(370);
        table.padLeft(30);
        table.add(levelOne).padRight(20);
        table.add(levelTwo).padRight(25);
        table.add(levelThree).padRight(15);


        img.setPosition(0, 0);
        pimg.setPosition(stage.getWidth()/2-100, stage.getHeight()-100);
        pimg.setSize(stage.getWidth()/3, stage.getHeight()/5);
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
