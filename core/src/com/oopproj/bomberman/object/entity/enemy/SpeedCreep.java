package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.Map;

public class SpeedCreep extends Enemy {
    private final float time = 2;
    private float dTime = time;

    public SpeedCreep(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 200;
        randomDir();
    }

    public int changeSpeed() {
        return MathUtils.random(100, 300);
    }

    @Override
    public void move(Map map) {
        dTime -= Gdx.graphics.getDeltaTime();
        if (dTime <= 0 || !checkMove(map, lastDirection)) {
            randomDir();
        }
        if (dTime <= 0) {
            movingSpeed = changeSpeed();
        }
        super.move(map);
        if (dTime <= 0) {
            dTime = time;
        }
    }
}
