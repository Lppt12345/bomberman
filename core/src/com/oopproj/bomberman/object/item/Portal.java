package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.data.Map;

public class Portal extends Item{
    public Portal(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    @Override
    public void powerUp(Map map) {
        super.powerUp(map);
    }

}
