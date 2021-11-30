package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.data.Assets;
import com.oopproj.bomberman.ui.Font;
import com.oopproj.bomberman.ui.GameSound;

public class BombermanGame extends Game {
    public SpriteBatch batch;
    public Assets assets;
    public Font font;
    public float renderAlpha;
    public GameSound sound;

    @Override
    public void create() {
        renderAlpha = 0;
        batch = new SpriteBatch();
        assets = new Assets();
        font = new Font("whitrabt.ttf", 75);
        sound = new GameSound();
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
