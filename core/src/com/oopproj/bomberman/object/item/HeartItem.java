package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.utils.Map;

public class HeartItem extends Item{
    public HeartItem(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    @Override
    public void powerUp(Map map) {
        super.powerUp(map);
        destroyed = true;
        if ( map.getPlayer().getLife() < 3){
            System.out.println(map.getPlayer().getLife());
            map.getPlayer().increaseLife();
            System.out.println("SAU KHI AN: "+ map.getPlayer().getLife());
        }
    }
}
