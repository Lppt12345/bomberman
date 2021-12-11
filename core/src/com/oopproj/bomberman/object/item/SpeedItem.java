package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.utils.Map;

public class SpeedItem extends Item {
    public SpeedItem(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    @Override
    public void powerUp(Map map) {
        super.powerUp(map);
        destroyed = true;
        map.getPlayer().increaseSpeed();
    }

}
