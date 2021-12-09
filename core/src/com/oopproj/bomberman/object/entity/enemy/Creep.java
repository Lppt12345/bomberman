package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;

public class Creep extends Enemy {

    public Creep(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 100;
        currentDirection = Direction.LEFT;
    }

    public void randomDir() {
        currentDirection = MathUtils.random(0, 3);
    }

    @Override
    public void move(Map map) {
        if (!checkMove(map, lastDirection)) {
            randomDir();
        }
        super.move(map);
    }

}
