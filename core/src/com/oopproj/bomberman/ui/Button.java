package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.oopproj.bomberman.data.State;

import java.util.ArrayList;
import java.util.List;

public class Button implements Disposable {
    private static final double DURATION = 0.5;
    private double delta;
    private final float x;
    private final float y;
    private float currentY;
    private float alpha;
    private Texture texture;
    private Rectangle rect;
    private SpriteBatch batch;
    private State state;
    private boolean isTouched;
    private boolean doneRendering;

    public Button(Texture texture, float x, float y) {
        rect = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.x = x - (float) texture.getWidth() / 2;
        this.y = y - (float) texture.getHeight() / 2;
        this.currentY = y - 50;
        this.doneRendering = false;
        this.alpha = 0;
        this.texture = texture;
        this.batch = new SpriteBatch();
        this.state = State.SLIDEIN;
        this.delta = 0;
        this.isTouched = false;
    }

    public void render() {
        batch.setColor(1, 1, 1, alpha);
        batch.begin();
//        batch.draw(texture,
//                x,
//                currentY);
        batch.draw(texture,
                rect.x - rect.width / 2,
                rect.y - rect.height / 2);
        batch.end();
    }

    public boolean process(List<Button> buttons) {
        switch (state) {
            case SLIDEIN: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), 0, DURATION);
                alpha = (float) parabol(delta);
                currentY = (float) (y - 50 + 50 * parabol(delta));
                rect.y = (float) (rect.y - 50 + 50 * parabol(delta));
                if (delta == DURATION) {
                    state = State.STATIC;
                    delta = 0;
                    doneRendering = true;
                }
                break;
            }
            case SLIDEOUT: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), DURATION, DURATION * 2);
                alpha = (float) parabol(delta);
                currentY = (float) (y - 50 + 50 * parabol(delta));
                for (Button button : buttons) {
                    if (button != this) {
                        button.setAlpha(this.getAlpha());
                    }
                }
                if (delta == DURATION * 2) {
                    state = State.DISAPPEARED;
                    delta = 0;
                    isTouched = true;
                }
                break;
            }
            case FADEOUT: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), DURATION, DURATION * 2);
                alpha = (float) parabol(delta);
                if (delta == DURATION * 2) {
                    state = State.DISAPPEARED;
                    delta = 0;
                }
                break;
            }
            case STATIC: {
                int mouseX = Gdx.input.getX();
                int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
                if (this.x <= mouseX && mouseX <= this.x + texture.getWidth()
                        && this.y <= mouseY && mouseY <= this.y + texture.getHeight()) {
                    if (Gdx.input.justTouched()) {
                        state = State.SLIDEOUT;
                        delta = 0;
                    } else {
                        // state = State.HOVER;
                    }
                }
                break;
            }
            case HOVER: {
                break;
            }
        }
        return isTouched;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public double parabol(double delta) {
        return (((-1) / Math.pow(DURATION, 2)) * Math.pow(delta, 2) + ((double) 2 / DURATION) * delta);
    }

    private float invertParabol(long delta) {
        return (float) ((1 / Math.pow(DURATION, 2)) * Math.pow(delta, 2) - (2 / DURATION) * delta) + 1;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getWidth() {
        return texture.getWidth();
    }

    public float getHeight() {
        return texture.getHeight();
    }

    public State getState() {
        return this.state;
    }

    public boolean isDoneRendering() {
        return doneRendering;
    }
}
