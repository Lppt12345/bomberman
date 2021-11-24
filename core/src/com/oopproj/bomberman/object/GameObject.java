package com.oopproj.bomberman.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.oopproj.bomberman.data.Assets;

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

    public abstract void render(SpriteBatch batch);
}
