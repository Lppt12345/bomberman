package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.entity.Entity;

public class Creep extends Enemy {
    // Xet huong mac dinh la sang trai gap cot thi doi huong ngau nhien
    public Creep(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 100;
        currentDirection = Direction.LEFT;
    }

    @Override
    public void move(Map map) {
        if (!checkMove(map , lastDirection)){
            randomDir();
        }
        super.move(map);
    }

}
