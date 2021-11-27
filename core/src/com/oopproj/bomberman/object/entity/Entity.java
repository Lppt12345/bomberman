package com.oopproj.bomberman.object.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.object.ground.Wall;
import com.oopproj.bomberman.ui.ScreenRes;

import java.util.List;


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
        pos.width = 50;
        pos.height = 50;
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
    public boolean checkMove(Map map){
        int posAtMap = getPositionAtMap(map);
        double limit = (double)   ScreenRes.scale / 5;
        Rectangle entity = null;
        GameObject tmp1 = null , tmp2 = null ;
        // Lay cot va hang tao bien check
        int col = map.getColumn();
        switch (lastDirection){
            case Direction.UP:{
                // Xet entity tren no 1 doan
                entity = new Rectangle(pos.x , pos.y + movingSpeed * Gdx.graphics.getDeltaTime(), pos.width , pos.height);
                if (posAtMap - col < 0){
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap - col);
                tmp2 = map.getMap().get(posAtMap - col + 1);

                // Check va cham vs 2 khoi tren bang cach kiem tra lan luot tung khoi
                if (!(tmp1 instanceof Grass)){
                    // Check di sang ben phai va Dieu chinh actor ve chinh giua
                    if (tmp2 instanceof Grass && Math.abs(tmp2.getPos().x - pos.x)  <= limit) {
                        pos.x = tmp2.getPos().x;
                    }else {
                        if (entity.overlaps(tmp1.getPos())){
                            return false;
                        }
                    }
                }
                if (!(tmp2 instanceof Grass)){
                    // Check di sang ben phai
                    if (tmp1 instanceof Grass && Math.abs(tmp1.getPos().x - pos.x)  <= limit){
                        pos.x = tmp1.getPos().x;
                    }else {
                        if (entity.overlaps(tmp2.getPos())){
                            return false;
                        }
                    }
                }
                break;
            }
            case Direction.DOWN:{
                // Xet entity duoi no 1 doan
                entity = new Rectangle(pos.x , pos.y - movingSpeed * Gdx.graphics.getDeltaTime(), pos.width , pos.height);
                if (posAtMap + col +1 > map.getMap().size()){
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap + col);
                tmp2 = map.getMap().get(posAtMap + col + 1);

                // Check va cham vs 2 khoi duoi
                if (!(tmp1 instanceof Grass)){
                    // Check di sang ben phai
                    if (tmp2 instanceof Grass && Math.abs(tmp2.getPos().x - pos.x)  <= limit) {
                        pos.x = tmp2.getPos().x;
                    }else {
                        if (entity.overlaps(tmp1.getPos())){
                            return false;
                        }
                  }
                }
                if (!(tmp2 instanceof Grass)){
                    // Check di sang ben trai
                    if (tmp1 instanceof Grass && Math.abs(tmp1.getPos().x - pos.x)  <= limit){
                        pos.x = tmp1.getPos().x;
                    }else {
                        if (entity.overlaps(tmp2.getPos())){
                            return false;
                        }
                    }
                }
                break;
            }
            case Direction.LEFT:{
                // Xet entity tren no 1 doan
                entity = new Rectangle(pos.x - movingSpeed* Gdx.graphics.getDeltaTime(), pos.y , pos.width , pos.height);
                if (posAtMap - 1 -col < 0){
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap - 1);
                tmp2 = map.getMap().get(posAtMap - 1  - col);

                // Check va cham vs 2 khoi duoi
                if (!(tmp1 instanceof Grass)){
                    // Check di sang ben phai
                    if (tmp2 instanceof Grass && Math.abs(tmp2.getPos().y - pos.y)  <= limit) {
                        pos.y = tmp2.getPos().y;
                    }else {
                        if (entity.overlaps(tmp1.getPos())){
                            return false;
                        }
                    }
                }
                if (!(tmp2 instanceof Grass)){
                    if (tmp1 instanceof Grass && Math.abs(tmp1.getPos().y - pos.y)  <= limit){
                        pos.y = tmp1.getPos().y;
                    }else {
                        if (entity.overlaps(tmp2.getPos())){
                            return false;
                        }
                    }
                }

                break;
            }
            case Direction.RIGHT:{
                // Xet entity tren no 1 doan
                entity = new Rectangle(pos.x + movingSpeed * Gdx.graphics.getDeltaTime(), pos.y , pos.width , pos.height);
                if (posAtMap + 1 - col < 0){
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap + 1);
                tmp2 = map.getMap().get(posAtMap + 1  - col);

                // Check va cham vs 2 khoi duoi
                if (!(tmp1 instanceof Grass)){
                    // Check di sang ben phai
                    if (tmp2 instanceof Grass && Math.abs(tmp2.getPos().y - pos.y)  <= limit) {
                        pos.y = tmp2.getPos().y;
                    }else {
                        if (entity.overlaps(tmp1.getPos())) {
                            return false;
                        }
                    }
                }
                if (!(tmp2 instanceof Grass)) {
                    // Check di sang ben phai
                    if (tmp1 instanceof Grass && Math.abs(tmp1.getPos().y - pos.y) <= limit){
                        pos.y = tmp1.getPos().y;
                    }else {
                        if (entity.overlaps(tmp2.getPos())) {
                            return false;
                        }
                    }
                }
                break;
            }
        }
        return true;
    }
    public void move(Map map) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) animation[lastDirection].getKeyFrame(stateTime, true);
        if (currentFrame == null) {
            currentFrame = frame[0][0];
        }

        switch (currentDirection) {
            case Direction.UP: {
                lastDirection = Direction.UP;
                animation[Direction.UP].setFrameDuration(animationSpeed);
                if (!checkMove(map)){
                    return;
                }
                pos.y += movingSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.DOWN: {
                lastDirection = Direction.DOWN;
                animation[Direction.DOWN].setFrameDuration(animationSpeed);
                if (!checkMove(map)){
                    return;
                }
                pos.y -= movingSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.LEFT: {
                lastDirection = Direction.LEFT;
                animation[Direction.LEFT].setFrameDuration(animationSpeed);
                if (!checkMove(map)){
                    return;
                }
                pos.x -= movingSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.RIGHT: {
                lastDirection = Direction.RIGHT;
                animation[Direction.RIGHT].setFrameDuration(animationSpeed);
                if (!checkMove(map)){
                    return;
                }
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
