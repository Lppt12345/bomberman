package com.oopproj.bomberman.object.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.ui.ScreenRes;

public abstract class Entity extends GameObject {
    protected TextureRegion[][] frame;
    protected TextureRegion currentFrame;
    protected Animation[] animation;
    protected float animationSpeed = 0.1f;
    protected float movingSpeed = 200;
    protected float stateTime = 0f;
    protected int currentDirection;
    protected int lastDirection = Direction.DOWN;

    public Entity(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, x, y);
        pos.width = ScreenRes.scale;
        pos.height = ScreenRes.scale;
        frame = TextureRegion.split(texture,
                texture.getWidth() / numberOfFrame,
                texture.getHeight() / 4);
        animation = new Animation[4];
        for (int i = 0; i < 4; i++) {
            animation[i] = new Animation<TextureRegion>(animationSpeed, frame[i]);
        }
    }

    public void setMovingSpeed(float speed) {
        this.movingSpeed = speed;
    }

    public void setAnimationSpeed(float speed) {
        this.animationSpeed = speed;
    }

    public void move() {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) animation[lastDirection].getKeyFrame(stateTime, true);
        if (currentFrame == null) {
            currentFrame = frame[0][0];
        }
        switch (currentDirection) {
            case Direction.UP: {
                lastDirection = Direction.UP;
                animation[Direction.UP].setFrameDuration(animationSpeed);
                pos.y += movingSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.DOWN: {
                lastDirection = Direction.DOWN;
                animation[Direction.DOWN].setFrameDuration(animationSpeed);
                pos.y -= movingSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.LEFT: {
                lastDirection = Direction.LEFT;
                animation[Direction.LEFT].setFrameDuration(animationSpeed);
                pos.x -= movingSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.RIGHT: {
                lastDirection = Direction.RIGHT;
                animation[Direction.RIGHT].setFrameDuration(animationSpeed);
                pos.x += movingSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            default: {
                animation[0].setFrameDuration(0);
                animation[1].setFrameDuration(0);
                animation[2].setFrameDuration(0);
                animation[3].setFrameDuration(0);
                currentFrame = frame[lastDirection][0];
                break;
            }
        }
    }


    public void render(SpriteBatch batch) {
        batch.setColor(1, 1, 1, 1);
        batch.draw(currentFrame, pos.x, pos.y);
    }

    public void dispose() {
        super.dispose();
    }
}
