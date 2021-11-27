package com.oopproj.bomberman.object.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class Wall extends GameObject {
    public Wall(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y);
    }


}
