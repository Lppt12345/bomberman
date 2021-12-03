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
    private float alpha;
    private Texture texture;
    private Rectangle rect;
    private Rectangle origin;
    private SpriteBatch batch;
    private State state;
    private boolean isTouched;
    private boolean doneRendering;

    public Button(Texture texture, float x, float y) {
        rect = new Rectangle(x - texture.getWidth() / 2, y - texture.getHeight() / 2, texture.getWidth(), texture.getHeight());
        origin = new Rectangle(rect);
        origin.x = x - origin.width / 2;
        origin.y = y - origin.height / 2;
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
        batch.draw(texture,
                rect.x,
                rect.y,
                rect.width,
                rect.height);
        batch.end();
    }

    public boolean process(List<Button> buttons) {
        switch (state) {
            case SLIDEIN: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), 0, DURATION);
                alpha = (float) parabol(delta);
                rect.y = (float) (origin.y - 50 + 50 * parabol(delta));
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
                rect.y = (float) (origin.y - 50 + 50 * parabol(delta));
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
                if (this.origin.x <= mouseX && mouseX <= this.origin.x + texture.getWidth()
                        && this.origin.y <= mouseY && mouseY <= this.origin.y + texture.getHeight()) {
                    state = State.HOVER;
                    delta = 0;
                } else {
                    if (rect.x < origin.x) {
                        rect.x += 1;
                    }
                    if (rect.y < origin.y) {
                        rect.y += 1;
                    }
                    if (rect.width > origin.width) {
                        rect.width -= 2;
                    }
                    if (rect.height > origin.height) {
                        rect.height -= 2;
                    }
                }
                break;
            }
            case HOVER: {
                int mouseX = Gdx.input.getX();
                int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
                if (this.origin.x <= mouseX && mouseX <= this.origin.x + texture.getWidth()
                        && this.origin.y <= mouseY && mouseY <= this.origin.y + texture.getHeight()) {
                    if (rect.x > origin.x - 5) {
                        rect.x -= 1;
                    }
                    if (rect.y > origin.y - 5) {
                        rect.y -= 1;
                    }
                    if (rect.width < origin.width + 10) {
                        rect.width += 2;
                    }
                    if (rect.height < origin.height + 10) {
                        rect.height += 2;
                    }
                    if (Gdx.input.justTouched()) {
                        state = State.SLIDEOUT;
                        delta = 0;
                    }
                } else {
                    state = State.STATIC;
                    delta = 0;
                }
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
