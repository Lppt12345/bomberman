package com.oopproj.bomberman.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.oopproj.bomberman.utils.State;

import java.util.List;

public abstract class UIElement implements Disposable {
    protected double delta = 0;
    protected float x;
    protected float y;
    protected float currentY;
    protected float alpha = 0;
    protected SpriteBatch batch = new SpriteBatch();
    protected State state = State.SLIDEIN;
    protected boolean doneRendering = false;
    protected boolean renderCalled = false;

    public abstract void render();

    public abstract Object process(List<UIElement> uiElements);

    public static double parabol(double delta, double DURATION) {
        return (((-1) / Math.pow(DURATION, 2)) * Math.pow(delta, 2) + ((double) 2 / DURATION) * delta);
    }

    public static float invertParabol(long delta, double DURATION) {
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

    public void setState(State state) {
        this.state = state;
        delta = 0;
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

    public abstract float getY();

    public void reset() {
        delta = 0;
        alpha = 0;
        state = State.SLIDEIN;
        doneRendering = false;
        renderCalled = false;
    }
}
