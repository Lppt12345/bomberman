package com.oopproj.bomberman.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.ui.Banner;
import com.oopproj.bomberman.ui.Button;
import com.oopproj.bomberman.ui.UIElement;
import com.oopproj.bomberman.utils.State;

import java.util.List;
import java.util.Queue;

public abstract class Scene implements Screen {
    protected static final double DURATION = 0.25;
    protected double d = 0;
    protected Scene prevScene;
    protected BombermanGame game;
    protected Texture background;
    protected State state;
    protected SpriteBatch batch;
    protected List<UIElement> uiElements;
    protected Queue<UIElement> renderOrder;

    public Scene(BombermanGame game, Texture background) {
        this.game = game;
        state = State.FADEIN;
        this.background = background;
        batch = new SpriteBatch();
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setColor(1, 1, 1, game.renderAlpha);

        sceneTransition();

        drawUIElements();
    }

    public void setPrevScene(Scene prevScene) {
        this.prevScene = prevScene;
    }

    public void sceneTransition() {
        if (this.state == State.FADEIN) {
            d = MathUtils.clamp(d + Gdx.graphics.getDeltaTime(), 0, DURATION);
            game.renderAlpha = (float) Button.parabol(d, DURATION);
            if (d == DURATION) {
                this.state = State.STATIC;
                d = 0;
            }
        }
        if (this.state == State.FADEOUT) {
            d = MathUtils.clamp(d + Gdx.graphics.getDeltaTime(), DURATION, DURATION * 2);
            game.renderAlpha = (float) Button.parabol(d, DURATION);
            if (d == DURATION * 2) {
                this.state = State.DISAPPEARED;
                d = 0;
            }
        }
    }

    public void drawUIElements() {
        batch.begin();
        if (background != null) {
            batch.draw(background, 0, 0);
        }
        batch.end();

        if (uiElements != null && renderOrder != null) {
            boolean isDoneRendering = true;
            for (UIElement e : uiElements) {
                e.render();
                if (e instanceof Banner) {
                    e.process(null);
                }
                if (!e.isDoneRendering()) {
                    isDoneRendering = false;
                    break;
                }
            }
            if (isDoneRendering && !renderOrder.isEmpty()) {
                uiElements.add(renderOrder.poll());
            }
        }
    }
}
