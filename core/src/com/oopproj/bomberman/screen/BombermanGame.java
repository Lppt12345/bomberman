package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.utils.Assets;
import com.oopproj.bomberman.ui.Font;
import com.oopproj.bomberman.ui.GameSound;

public class BombermanGame extends Game {
    public SpriteBatch batch;
    public Assets assets;
    public float renderAlpha;

    @Override
    public void create() {
        renderAlpha = 0;
        batch = new SpriteBatch();
        assets = Assets.getInstance();
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
