package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.State;

import java.util.List;

public class TextInput extends UIElement {
    private final double DURATION = 0.25;

    private Texture texture;
    private Font font;
    private StringBuilder string;
    private String hint;

    public TextInput(Texture texture, String hint, float x, float y) {
        this.x = x - (float) texture.getWidth() / 2;
        this.y = y - (float) texture.getHeight() / 2;
        this.currentY = y - 50;
        this.texture = texture;
        string = new StringBuilder();
        font = new Font("fonts/whitrabt.ttf", 25);
        this.hint = hint;
    }

    @Override
    public void render() {
        renderCalled = true;
        batch.setColor(1, 1, 1, alpha);
        batch.begin();
        batch.draw(texture, x, currentY);
        batch.end();
        if (string.toString().isEmpty()) {
            font.setColor(0.5f, 0.5f, 0.5f, alpha);
            font.draw(hint, this.getX(), this.getCurrentY() + 17);
        } else {
            font.setColor(1, 1, 1, alpha);
            font.draw(string.toString(), this.getX(), this.getCurrentY() + 17);
        }
    }

    @Override
    public Object process(List<UIElement> uiElements) {
        if (!renderCalled) {
            return null;
        }
        switch (state) {
            case SLIDEIN: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), 0, DURATION);
                alpha = (float) parabol(delta, DURATION);
                currentY = (float) (y - 50 + 50 * parabol(delta, DURATION));
                if (delta == DURATION) {
                    state = State.STATIC;
                    delta = 0;
                    doneRendering = true;
                }
                break;
            }
            case SLIDEOUT: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), DURATION, DURATION * 2);
                alpha = (float) parabol(delta, DURATION);
                currentY = (float) (y - 50 + 50 * parabol(delta, DURATION));
                for (UIElement element : uiElements) {
                    if (element != this) {
                        element.setAlpha(this.getAlpha());
                    }
                }
                if (delta == DURATION * 2) {
                    state = State.DISAPPEARED;
                    delta = 0;
                    return string.toString();
                }
                break;
            }
            case FADEOUT: {
                delta = MathUtils.clamp(delta + Gdx.graphics.getDeltaTime(), DURATION, DURATION * 2);
                alpha = (float) parabol(delta, DURATION);
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
                // A - 29
                // Z - 54
                for (int i = 29; i <= 54; i++) {
                    if (Gdx.input.isKeyJustPressed(i)) {
                        if (string.length() < 15) {
                            string.append((char) (i + 68));
                        }
                    }
                }
                // 0 - 7
                // 9 - 16
                for (int i = 7; i <= 16; i++) {
                    if (Gdx.input.isKeyJustPressed(i)) {
                        if (string.length() < 15) {
                            string.append((char) (i + 41));
                        }
                    }
                }

                if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
                    try {
                        string.deleteCharAt(string.length() - 1);
                    } catch (StringIndexOutOfBoundsException e) {
                        break;
                    }
                }

                if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                    this.state = State.SLIDEOUT;
                    delta = 0;
                }
                break;
            }
            case DISAPPEARED: {
                return string.toString();
            }
        }
        return null;
    }

    @Override
    public float getCurrentY() {
        return this.currentY + (float) texture.getHeight() / 2;
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
    public float getY() {
        return this.y + (float) texture.getHeight() / 2;
    }

    @Override
    public void dispose() {

    }
}
