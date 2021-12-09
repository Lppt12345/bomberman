package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.utils.State;

public abstract class Scene implements Screen {
    protected Scene prevScene;
    protected BombermanGame game;
    protected Texture background;
    protected State state;
    protected SpriteBatch batch;

    public Scene(BombermanGame game, Texture background) {
        this.game = game;
        state = State.FADEIN;
        this.background = background;
        batch = new SpriteBatch();
    }

    public void render(float delta) {
        if (this.state == State.FADEIN) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha + Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 1) {
                this.state = State.STATIC;
            }
        }
        if (this.state == State.FADEOUT) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha - Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 0) {
                this.state = State.DISAPPEARED;
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);
        batch.setColor(1, 1, 1, game.renderAlpha);

        batch.begin();
        if (background != null) {
            batch.draw(background, 0, 0);
        }
        batch.end();
    }

    public void setPrevScene(Scene prevScene) {
        this.prevScene = prevScene;
    }
}
