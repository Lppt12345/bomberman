package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;


public class Ballom extends  Enemy{
    private int time ;
    public Ballom(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 100;
        currentDirection = Direction.LEFT;
        time = 5;
    }

    @Override
    public void move(Map map) {

        if (!checkMove(map, lastDirection)) {
            randomDir();
        }
//        if (map.getPlayer().getCurrentDirection() == Direction.UP){
//            currentDirection = Direction.DOWN;
//        }
//        if (map.getPlayer().getCurrentDirection() == Direction.DOWN){
//            currentDirection = Direction.UP;
//        }
//        if (map.getPlayer().getCurrentDirection() == Direction.RIGHT){
//            currentDirection = Direction.LEFT;
//        }
//        if (map.getPlayer().getCurrentDirection() == Direction.LEFT){
//            currentDirection = Direction.RIGHT;
//        }
        super.move(map);
    }

}
