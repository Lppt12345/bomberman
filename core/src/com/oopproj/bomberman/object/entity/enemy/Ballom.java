package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.entity.ai.AiLow;


public class Ballom extends  Enemy{
    private int time ;
    private AiLow aiLow;
    public Ballom(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 100;
        time = 100;
        currentDirection = Direction.LEFT;
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
        time --;
        aiLow = new AiLow(map , this);
        if (time == 0) {
            currentDirection = aiLow.calculateDir(map, this);
            time = 100;
        }
        if (!checkMove(map, lastDirection)){
            switchDir();
        }
        super.move(map);
    }

}
