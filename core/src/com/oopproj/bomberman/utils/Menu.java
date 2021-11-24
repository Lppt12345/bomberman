package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.ui.Button;

public class Menu implements Screen {
    private BombermanGame game;
    Button button;

    public Menu(BombermanGame game) {
        this.game = game;
        Texture texture = new Texture(Gdx.files.internal("grass.png"));
        button = new Button(texture, 50, 50);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        button.render(game.batch);
        game.batch.end();
        if (button.process()) {
            game.setScreen(new Gameplay(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
