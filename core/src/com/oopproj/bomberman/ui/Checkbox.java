package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.State;

import java.util.List;

public class Checkbox extends UIElement {
    private final double DURATION = 0.25;
    boolean isChecked;
    private Font font;
    private String title;
    private Texture texture;
    private TextureRegion[] textureRegion;

    public Checkbox(Texture texture, String title, boolean isChecked, float x, float y) {
        this.x = x - (float) texture.getWidth() / 4;
        this.y = y - (float) texture.getHeight() / 2;
        this.currentY = y - 50;
        this.texture = texture;
        textureRegion = new TextureRegion[2];
        textureRegion[0] = new TextureRegion(texture, 0, 0, texture.getWidth() / 2, texture.getHeight());
        textureRegion[1] = new TextureRegion(texture, texture.getWidth() / 2, 0, texture.getWidth() / 2, texture.getHeight());
        this.isChecked = isChecked;
        font = new Font("fonts/whitrabt.ttf", 16);
        this.title = title;
    }

    @Override
    public void render() {
        renderCalled = true;
        batch.setColor(1, 1, 1, alpha);
        batch.begin();
        if (isChecked) {
            batch.draw(textureRegion[1], x, currentY);
        } else {
            batch.draw(textureRegion[0], x, currentY);
        }
        batch.end();
        font.setColor(1, 1, 1, alpha);
        font.draw(title, getX(), currentY);
    }

    @Override
    public Object process(List<UIElement> uiElements) {
        if (!renderCalled) {
            return isChecked;
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
                        break;
                    }
                }
                if (!done) {
                    break;
                }
                if (Gdx.input.justTouched()) {
                    int mouseX = Gdx.input.getX();
                    int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
                    if (this.x <= mouseX && mouseX <= this.x + texture.getWidth() / 2f
                            && this.y <= mouseY && mouseY <= this.y + texture.getHeight()) {
                        isChecked = !isChecked;
                    }
                }
                break;
            }
        }
        return isChecked;
    }

    @Override
    public float getCurrentY() {
        return currentY + (float) texture.getHeight() / 2;
    }

    @Override
    public float getWidth() {
        return texture.getWidth() / 2f;
    }

    @Override
    public float getHeight() {
        return texture.getHeight() / 2f;
    }

    @Override
    public float getX() {
        return this.x + (float) texture.getWidth() / 4;
    }

    @Override
    public float getY() {
        return this.y + (float) texture.getHeight() / 2;
    }

    @Override
    public void dispose() {

    }
}
