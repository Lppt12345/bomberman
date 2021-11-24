package com.oopproj.bomberman.object.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class Brick extends GameObject {

    public Brick(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y);
    }

    @Override
    public void dispose() {

    }
}
