package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.data.Map;

public class SpeedCreep extends Enemy {
    private int time = 60;

    public SpeedCreep(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 50;
    }
    public int changeSpeed(){
        return MathUtils.random(50, 200);
    }

    @Override
    public void move(Map map){
        time --;
        if (time == 0 || !checkMove(map , lastDirection) ){
            randomDir();
        }
        if (time == 0){
            movingSpeed = changeSpeed();
            System.out.println(movingSpeed);
        }
        super.move(map);
        if (time == 0){
            time = 200;
        }
    }
}
