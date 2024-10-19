package com.AngryBirds;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class MainTop extends Game {
    @Override
    public void create() {
        this.setScreen(new Main(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
