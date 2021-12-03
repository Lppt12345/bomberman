package com.oopproj.bomberman.object.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class Brick extends GameObject {
    public enum BrickState {
        DESTROYED, NORMAL
    }

    private BrickState state = BrickState.NORMAL;

    public BrickState getState() {
        return state;
    }

    public void setState(BrickState state) {
        this.state = state;
    }

    public Brick(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y);
    }


}
