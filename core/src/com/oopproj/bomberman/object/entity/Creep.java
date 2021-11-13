package com.oopproj.bomberman.object.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Creep extends Entity {
    public Creep(Texture texture, int numberOfFrame, int x, int y) {
        super(texture, numberOfFrame, x, y);
    }

    @Override
    public void move() {
        float time = 0;
        if (time >= 1) {
            currentDirection = MathUtils.random(0, 4);
            time = 0;
        } else {
            time += Gdx.graphics.getDeltaTime();
        }
        super.move();
    }

    @Override
    public void dispose() {

    }
}
