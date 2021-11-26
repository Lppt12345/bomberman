package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.data.Assets;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.entity.Bomber;

public class Gameplay implements Screen {
    private BombermanGame game;
    private Bomber bomber;

    private OrthographicCamera camera;

    public Gameplay(BombermanGame game) {
        this.game = game;

        bomber = new Bomber(game.assets.get(Assets.BMAN), 8, 0, 0);

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        bomber.move();

        game.batch.begin();
        bomber.render(game.batch);
        game.batch.end();
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
