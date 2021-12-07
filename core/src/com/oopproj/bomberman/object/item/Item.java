package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.ground.Brick;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.ui.GameSound;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.utils.Assets;
import com.oopproj.bomberman.utils.Map;

public abstract class Item extends GameObject {
    protected boolean destroyed = false;
    protected Assets assets;
    private ItemState state;
    public Item(Texture texture, float x, float y) {
        super(texture, x + Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2
                , y + Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2);
//      pos.x = x +  Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2;
//      pos.y  = y +  Math.abs(ScreenRes.scale - (float) texture.getHeight()) / 2 ;
        state = ItemState.HIDE;
        assets = Assets.getInstance();
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void collisionWithBomBer(Map map) {
        if (getPos().overlaps(map.getPlayer().getPos())) {
            powerUp(map);
            destroyed = true;
        }
        if (map.getMap().get(getPositionAtMap(map)) instanceof Grass) {
            state = ItemState.SHOW;
        }
        if (map.getMap().get(getPositionAtMap(map)) instanceof Brick) {
            state = ItemState.HIDE;
        }
    }

    public ItemState getState() {
        return state;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (state == ItemState.SHOW) {
            batch.draw(texture, pos.x, pos.y);
        }
    }

    public void powerUp(Map map) {
        GameSound.playPowerUp();
    }

    public enum ItemState {
        HIDE, SHOW, DESTROY
    }

}
