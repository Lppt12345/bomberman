package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.entity.Entity;

public class Creep extends Enemy {
    public Creep(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
    }

    @Override
    public void move(Map map) {
        float time = 0;
        if (time >= 1) {
            currentDirection = MathUtils.random(0, 4);
            time = 0;
        } else {
            time += Gdx.graphics.getDeltaTime();
        }
        super.move(map);
    }

    @Override
    public void dispose() {

    }
}
