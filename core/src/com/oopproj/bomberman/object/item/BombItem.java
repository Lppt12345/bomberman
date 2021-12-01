package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class BombItem extends GameObject {
    public BombItem(Texture texture, float x, float y) {
        super(texture, x, y);
        pos.x = pos.x + Math.abs((pos.getWidth() - texture.getWidth()) / 2);
        pos.y = pos.y + Math.abs((pos.getHeight() - texture.getHeight()) / 2);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y);
    }
}
