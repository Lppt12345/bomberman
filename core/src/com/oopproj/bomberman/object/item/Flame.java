package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oopproj.bomberman.object.GameObject;

public class Flame extends GameObject {
    private TextureRegion[] frame;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> animation;
    private float stateTime;

    public Flame(Texture texture, float x, float y) {
        super(texture, x, y);
        pos.width = (float) texture.getWidth() / 5;
        pos.height = (float) texture.getHeight();
        TextureRegion[][] temp = TextureRegion.split(texture,
                texture.getWidth() / 5,
                texture.getHeight());
        frame = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            frame[i] = temp[0][i];
        }
        animation = new Animation<TextureRegion>((float) 0.1f, frame);
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, pos.x, pos.y);
    }
}
