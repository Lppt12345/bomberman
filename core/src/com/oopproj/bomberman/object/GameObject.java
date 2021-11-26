package com.oopproj.bomberman.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.oopproj.bomberman.data.Assets;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.ui.ScreenRes;

public abstract class GameObject implements Disposable {
    protected Rectangle pos;
    protected Texture texture;
    public GameObject(Texture texture, float x, float y) {
        this.texture = texture;
        pos = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void setPos(float x, float y) {
        pos.setPosition(x, y);
    }

    /**
     * Tim vi tri cua phan tu tren mang 1 chieu map de anh xa
     * @return vi tri.
     */
    public int getPositionAtMap() {
        int x = (int) pos.x / ScreenRes.scale;
        int y = (int) pos.y / ScreenRes.scale;
        return Map.getColumn() * (Map.getRow() - 1 - y) + x;
    }

    public abstract void render(SpriteBatch batch);
}
