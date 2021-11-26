package com.oopproj.bomberman.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.oopproj.bomberman.utils.ScreenRes;

public class Button implements Disposable {
    private static final float DURATION = 1000;
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
        System.out.println(startTime);
        this.alpha = 0;
        this.texture = texture;
    }

    public void render(SpriteBatch batch) {
        endTime = System.currentTimeMillis();
        long delta = endTime - startTime;
        if (delta < DURATION) {
            alpha = parabol(delta);
            currentY = y - 50 + 50 * parabol(delta);
        }
        batch.setColor(1, 1, 1, alpha);
        batch.draw(texture, x, currentY);
    }

    public boolean process() {
        if (Gdx.input.isTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = ScreenRes.getHeight() - Gdx.input.getY();
            System.out.println(mouseX + " " + mouseY);
            return x <= mouseX && mouseX <= x + texture.getWidth()
                    && y <= mouseY && mouseY <= y + texture.getHeight();
        }
        return false;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    private float parabol(long delta) {
        return (float) (((-1) / Math.pow(DURATION, 2)) * Math.pow(delta, 2) + (2 / DURATION) * delta);
    }
}
