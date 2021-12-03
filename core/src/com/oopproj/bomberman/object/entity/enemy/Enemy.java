package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.object.entity.Entity;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int score;
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

    public int getScore() {
        return score;
    }


    public void randomDir() {
//        Random random =new Random();
//        currentDirection = random.nextInt(3);
        currentDirection = MathUtils.random(0, 3);
//        System.out.println(currentDirection);
    }


    public Enemy(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
    }
}
