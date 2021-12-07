package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;
import com.oopproj.bomberman.object.entity.ai.AiLow;

public class Ballom extends  Enemy{
    private int time ;
    private AiLow aiLow = null;
    public Ballom(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        time = 120;
        score = 100;
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
        if (!checkMove(map, lastDirection) || time == 0){
            currentDirection = aiLow.calculateDir();
        }
        if (time == 0){
            time = 120;
        }
        super.move(map);
    }

}
