package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class Bomb extends GameObject {
    private double timeToExplore = 100;

    public Bomb(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    public double getTimeToExplore() {
        return timeToExplore;
    }

    public void setTimeToExplore(double timeToExplore) {
        this.timeToExplore = timeToExplore;
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
