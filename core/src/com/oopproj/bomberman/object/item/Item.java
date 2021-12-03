package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.ground.Brick;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.ui.ScreenRes;

public abstract class Item extends GameObject {
    public enum ItemState {
        HIDE, SHOW , DESTROY
    }
    private boolean isShow = false;
    private boolean destroyed = false;
    private ItemState state;
    public Item(Texture texture, float x, float y) {
        super(texture, x + Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2
                , y + Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2);
//      pos.x = x +  Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2;
//      pos.y  = y +  Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2 ;
        state = ItemState.HIDE;
    }

    public void collisionWithBomBer(Map map){
        if (getPos().overlaps(map.getPlayer().getPos())){
            powerUp(map);
            destroyed = true;
        }
        if (map.getMap().get(getPositionAtMap(map)) instanceof Grass){
            state = ItemState.SHOW;
        }
        if (map.getMap().get(getPositionAtMap(map)) instanceof Brick){
            state = ItemState.HIDE;
        }
    }

    public ItemState getState() {
        return state;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (state == ItemState.SHOW){
            batch.draw(texture , pos.x , pos.y);
        }
    }
    public abstract void powerUp(Map map);

}
