package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;


public class Doll extends Enemy {
    private final float time = 4;
    float dTime = time;

    public Doll(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 200;
        score = 200;
        randomDir();
    }

    public void switchDir() {
        switch (lastDirection) {
            case Direction.UP:
                currentDirection = Direction.DOWN;
                break;
            case Direction.DOWN:
                currentDirection = Direction.UP;
                break;
            case Direction.LEFT:
                currentDirection = Direction.RIGHT;
                break;
            case Direction.RIGHT:
                currentDirection = Direction.LEFT;
                break;
            default:
                break;
        }
    }

    @Override
    public void move(Map map) {
        dTime -= Gdx.graphics.getDeltaTime();
        if (!checkMove(map, lastDirection)) {
            randomDir();
        }
        if (dTime <= 0) {
            switchDir();
        }
        super.move(map);
        if (dTime <= 0) {
            dTime = time;
        }
    }

}
