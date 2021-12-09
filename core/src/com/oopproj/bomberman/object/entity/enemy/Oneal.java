package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.Map;


public class Oneal extends Enemy {
    protected int time;

    public Oneal(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 100;
        score = 200;
        time = 60;
    }

    public void randomDir() {
        currentDirection = MathUtils.random(0, 3);
    }

    @Override
    public void move(Map map) {
        time--;
        if (time == 0 || !checkMove(map, lastDirection)) {
            randomDir();
        }
        super.move(map);
        if (time == 0) {
            time = 60;
        }
    }

}
