package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.State;

import java.util.List;

public class Slider extends UIElement {
    protected final double DURATION = 0.3;
    private Texture slideBar;
    private Texture slider;
    private float sliderX;
    private float sliderY;

    public Slider(Texture slideBar, Texture slider, float x, float y, float value) {
        this.slider = slider;
        this.slideBar = slideBar;
        this.x = x - (float) slideBar.getWidth() / 2;
        this.y = y - (float) slideBar.getHeight() / 2;
        this.sliderX = MathUtils.clamp(
                this.x + 4 + (slideBar.getWidth() - 4 * 2) * value,
                this.x + 4,
                this.x + slideBar.getWidth() - 14
        );
        this.sliderY = y - 7;
        this.currentY = y - 50;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {
        renderCalled = true;
        batch.setColor(1, 1, 1, alpha);
        batch.begin();
        batch.draw(slideBar, x, currentY);
        batch.draw(slider, sliderX, currentY - 7);
        batch.end();
    }

    @Override
    public Object process(List<UIElement> uiElements) {
        if (!renderCalled) {
            return (sliderX - (x + 4)) / (slideBar.getWidth() - 14);
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
                if (Gdx.input.isTouched()) {
                    int mouseX = Gdx.input.getX();
                    int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
                    if ((this.x <= mouseX && mouseX <= this.x + slideBar.getWidth()
                            && this.y <= mouseY && mouseY <= this.y + slideBar.getHeight())
                            || sliderX <= mouseX && mouseX <= sliderX + slider.getWidth()
                            && sliderY <= mouseY && mouseY <= sliderY + slider.getHeight()) {
                        sliderX = MathUtils.clamp(mouseX - 5, x + 4, x + slideBar.getWidth() - 14);
                    }
                }
                break;
            }
        }
        return (sliderX - (x + 4)) / (slideBar.getWidth() - 14);
    }

    @Override
    public float getCurrentY() {
        return this.currentY + (float) slideBar.getHeight() / 2;
    }

    @Override
    public float getWidth() {
        return slideBar.getWidth();
    }

    @Override
    public float getHeight() {
        return slider.getHeight();
    }

    @Override
    public float getX() {
        return this.x + (float) slideBar.getWidth() / 2;
    }

    @Override
    public float getY() {
        return this.y + (float) slideBar.getHeight() / 2;
    }

    @Override
    public void reset() {
        super.reset();
        currentY = y - 50;
    }
}
