package com.oopproj.bomberman.object.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;

public class Grass extends GameObject {

    public Grass(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y);
    }


}
