package com.oopproj.bomberman.object.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.data.Direction;

public class Bomber extends Entity {

    public Bomber(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
    }

    @Override
    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentDirection = Direction.UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentDirection = Direction.DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentDirection = Direction.LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentDirection = Direction.RIGHT;
        } else {
            currentDirection = Direction.NOTMOVE;
        }
        super.move();
    }

    @Override
    public void dispose() {

    }
}
