package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.ScreenRes;

public class Menu implements Screen {
    private BombermanGame game;
    Button button;
    Texture background;

    public Menu(BombermanGame game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("ui/background.png"));
        button = new Button(new Texture(Gdx.files.internal("ui/play.png")), ScreenRes.getWidth() / 2, 100);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        button.render(game.batch);
        game.batch.end();
        if (button.process()) {
            try {
                game.setScreen(new Gameplay(game));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
