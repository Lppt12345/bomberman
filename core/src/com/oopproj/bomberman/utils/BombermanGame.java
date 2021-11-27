package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.data.Assets;
import com.oopproj.bomberman.ui.Font;

public class BombermanGame extends Game {
    public SpriteBatch batch;
    public Assets assets;
    public Font font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assets = new Assets();
        font = new Font("whitrabt.ttf", 50);
        this.setScreen(new Menu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
