package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oopproj.bomberman.object.GameObject;

public class Bomb extends GameObject {
    private float timeToExplode = 3;
    private boolean isExploded = false;
    private Flame flameUp;
    private Flame flameDown;
    private Flame flameLeft;
    private Flame flameRight;
    private TextureRegion[] frame;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> animation;
    private float stateTime;

    public Bomb(Texture texture, float x, float y) {
        super(texture, x, y);
        pos.width = (float) texture.getWidth() / 3;
        pos.height = (float) texture.getHeight();
        TextureRegion[][] temp = TextureRegion.split(texture,
                texture.getWidth() / 3,
                texture.getHeight());
        frame = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            frame[i] = temp[0][i];
        }
        animation = new Animation<TextureRegion>((float) timeToExplode / 3, frame);
        stateTime = 0f;
    }

    public double getTimeToExplore() {
        return timeToExplode;
    }

    public void setTimeToExplore(float timeToExplore) {
        this.timeToExplode = timeToExplore;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (stateTime > timeToExplode) {
            isExploded = true;
        }
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, false);
        batch.draw(currentFrame, pos.x, pos.y);
    }

    public boolean isExploded() {
        return isExploded;
    }
}
