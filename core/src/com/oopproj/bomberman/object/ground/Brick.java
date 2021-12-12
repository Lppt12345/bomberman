package com.oopproj.bomberman.object.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class Brick extends GameObject {
    private BrickState state = BrickState.NORMAL;

    public Brick(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    public BrickState getState() {
        return state;
    }

    public void setState(BrickState state) {
        this.state = state;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y);
    }

    public enum BrickState {
        DESTROYED, NORMAL
    }


}
