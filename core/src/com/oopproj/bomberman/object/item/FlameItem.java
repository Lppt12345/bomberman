package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.GameObject;

public class FlameItem extends Item {
    public FlameItem(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    @Override
    public void powerUp(Map map) {
        map.getPlayer().increaseFlameLength();
    }

}
