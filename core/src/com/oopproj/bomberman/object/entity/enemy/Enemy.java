package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.object.entity.Entity;

public abstract class Enemy extends Entity {

    public Enemy(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
    }
}
