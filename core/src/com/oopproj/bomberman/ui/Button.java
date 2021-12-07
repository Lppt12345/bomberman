package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.State;

import java.util.List;

public class Button extends UIElement {
    private Texture texture;
    private boolean isTouched;

    public Button(Texture texture, float x, float y) {
        this.x = x - (float) texture.getWidth() / 2;
        this.y = y - (float) texture.getHeight() / 2;
        this.currentY = y - 50;
        this.texture = texture;
        this.isTouched = false;
    }

    public void render() {
        batch.setColor(1, 1, 1, alpha);
        batch.begin();
        batch.draw(texture, x, currentY);
        batch.end();
    }

    public Object process(List<UIElement> uiElements) {
        switch (state) {
            case SLIDEIN: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), 0, DURATION);
                alpha = (float) parabol(delta);
                currentY = (float) (y - 50 + 50 * parabol(delta));
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
                for (UIElement element : uiElements) {
                    if (element != this) {
                        element.setAlpha(this.getAlpha());
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
                boolean done = true;
                for (UIElement e : uiElements) {
                    if (!e.isDoneRendering()) {
                        done = false;
                    }
                }
                if (!done) {
                    break;
                }
                if (Gdx.input.isTouched()) {
                    int mouseX = Gdx.input.getX();
                    int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
                    if (this.x <= mouseX && mouseX <= this.x + texture.getWidth()
                            && this.y <= mouseY && mouseY <= this.y + texture.getHeight()) {
                        state = State.SLIDEOUT;
                        delta = 0;
                    }
                }
                break;
            }
        }
        return isTouched;
    }

    @Override
    public float getWidth() {
        return texture.getWidth();
    }

    @Override
    public float getHeight() {
        return texture.getHeight();
    }

    @Override
    public float getX() {
        return this.x + (float) texture.getWidth() / 2;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}