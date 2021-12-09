package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.Map;

public class SpeedCreep extends Enemy {
    private float time = 3;

    public SpeedCreep(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 50;
        score = 150;
    }

    public int changeSpeed() {
        return MathUtils.random(50, 200);
    }

    public void randomDir() {
        currentDirection = MathUtils.random(0, 3);
    }

    @Override
    public void move(Map map) {
        time -= Gdx.graphics.getDeltaTime();
        if (time == 0 || !checkMove(map, lastDirection)) {
            randomDir();
        }
        if (time == 0) {
            movingSpeed = changeSpeed();
        }
        super.move(map);
        if (time == 0) {
            time = 3;
        }
    }
}
