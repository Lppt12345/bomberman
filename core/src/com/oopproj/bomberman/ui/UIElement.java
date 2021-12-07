package com.oopproj.bomberman.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.oopproj.bomberman.utils.State;

import java.util.List;

public abstract class UIElement implements Disposable {
    protected static final double DURATION = 0.5;
    protected double delta = 0;
    protected float x;
    protected float y;
    protected float currentY;
    protected float alpha = 0;
    protected SpriteBatch batch = new SpriteBatch();
    protected State state = State.SLIDEIN;
    protected boolean doneRendering = false;

    public abstract void render();

    public abstract Object process(List<UIElement> uiElements);

    public double parabol(double delta) {
        return (((-1) / Math.pow(DURATION, 2)) * Math.pow(delta, 2) + ((double) 2 / DURATION) * delta);
    }

    protected float invertParabol(long delta) {
        return (float) ((1 / Math.pow(DURATION, 2)) * Math.pow(delta, 2) - (2 / DURATION) * delta) + 1;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public State getState() {
        return this.state;
    }

    public boolean isDoneRendering() {
        return doneRendering;
    }

    public float getCurrentY() {
        return currentY;
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract float getX();

    public void reset() {
        delta = 0;
        alpha = 0;
        state = State.SLIDEIN;
        doneRendering = false;
    }
}
