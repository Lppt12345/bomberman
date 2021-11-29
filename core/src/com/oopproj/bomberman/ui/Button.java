package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Button implements Disposable {
    private static final int DURATION = 500;
    private long startTime;
    private long endTime;
    private final float x;
    private final float y;
    private float currentY;
    private float alpha;
    private Texture texture;
    private boolean touched;

    public Button(Texture texture, float x, float y) {
        this.x = x;
        this.y = y;
        currentY = y - 50;
        startTime = System.currentTimeMillis();
        this.alpha = 0;
        this.texture = texture;
        touched = false;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(1, 1, 1, alpha);
        batch.draw(texture, x, currentY);
    }

    public boolean process() {
        if (!touched) {
            endTime = System.currentTimeMillis();
            long delta = endTime - startTime;
            if (delta > DURATION - 20 && delta < DURATION) {
                delta = DURATION;
            }
            if (delta <= DURATION) {
                alpha = (float) parabol(delta);
                System.out.println(delta + " " + alpha);
                currentY = (float) (y - 50 + 50 * parabol(delta));
            }
        } else {
            endTime = System.currentTimeMillis();
            long delta = endTime - startTime;
            if (delta > DURATION - 20 && delta < DURATION) {
                delta = DURATION;
            }
            if (delta <= DURATION) {
                alpha = (float) parabol(delta + (long) DURATION);
                System.out.println(delta + " " + alpha);
                currentY = (float) (y - 50 + 50 * parabol(delta + (long) DURATION));
            } else {
                return true;
            }
        }

        if (Gdx.input.isTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
            startTime = System.currentTimeMillis();
            touched = x <= mouseX && mouseX <= x + texture.getWidth()
                    && y <= mouseY && mouseY <= y + texture.getHeight();
        }

        return false;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    private double parabol(long delta) {
        return (((-1) / Math.pow(DURATION, 2)) * Math.pow(delta, 2) + ((double) 2 / DURATION) * delta);
    }

    private float invertParabol(long delta) {
        return (float) ((1 / Math.pow(DURATION, 2)) * Math.pow(delta, 2) - (2 / DURATION) * delta) + 1;
    }

    public float getAlpha() {
        return this.alpha;
    }
}
