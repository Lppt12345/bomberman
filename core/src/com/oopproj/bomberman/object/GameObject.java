package com.oopproj.bomberman.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.ui.ScreenRes;

public abstract class GameObject {
    protected Rectangle pos;
    protected Texture texture;

    public GameObject(Texture texture, float x, float y) {
        this.texture = texture;
        pos = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void setPos(float x, float y) {
        pos.setPosition(x, y);
    }

    public Rectangle getPos() {
        return pos;
    }

    public int getPositionAtMap(Map map) {
        int x = (int) pos.x / ScreenRes.scale;
        int y = (int) pos.y / ScreenRes.scale;
        return map.getColumn() * (map.getRow() - 1 - y) + x;
    }

    public abstract void render(SpriteBatch batch);
}
