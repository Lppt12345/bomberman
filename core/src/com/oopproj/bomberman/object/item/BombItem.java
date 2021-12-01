package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class BombItem extends GameObject {
    public BombItem(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
