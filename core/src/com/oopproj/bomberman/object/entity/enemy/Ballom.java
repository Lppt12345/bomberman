package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;

public class Ballom extends  Enemy{
    public Ballom(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 100;
        currentDirection = Direction.LEFT;
    }

    @Override
    public void move(Map map) {
        if (!checkMove(map, lastDirection)) {
            randomDir();
        }else {
            if (pos.x > map.getPlayer().getPos().x){
                currentDirection = Direction.LEFT;
            }else {
                if (pos.x < map.getPlayer().getPos().x){
                    currentDirection = Direction.RIGHT;
                }
            }

            if (pos.y > map.getPlayer().getPos().y){
                currentDirection = Direction.DOWN;
            }else {
                if (pos.y < map.getPlayer().getPos().y){
                    currentDirection = Direction.UP;
                }
            }
        }
        super.move(map);
    }

}
