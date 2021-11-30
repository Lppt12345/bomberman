package com.oopproj.bomberman.object.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.ground.Grass;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends GameObject {
    public enum BombState {
        PLACED, BURNING, DISAPPEARED
    }

    private float timeToExplode = 3;
    private boolean isExploded = false;
    private List<Flame> flameUp;
    private List<Flame> flameDown = new ArrayList<>();
    private List<Flame> flameLeft = new ArrayList<>();
    private List<Flame> flameRight = new ArrayList<>();
    private TextureRegion[] frame;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private BombState state;

    public Bomb(Texture texture, float x, float y, int sizeFlame, Map map) {
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
        state = BombState.PLACED;
    }

    public double getTimeToExplore() {
        return timeToExplode;
    }

    public void setTimeToExplore(float timeToExplore) {
        this.timeToExplode = timeToExplore;
    }
    public void setFlame(Map map , int sizeFlame){
        Texture t = new Texture(Gdx.files.internal("flame.png"));
        for (int i = 0; i < getLengthFlame(map, Direction.UP, sizeFlame)){
            flameUp.add(new Flame(t, pos.x , pos.y + ))
        }
        for (int i = 0; i < getLengthFlame(map, Direction.DOWN, sizeFlame)){
            flameUp.add(new Flame(t, ))
        }
        for (int i = 0; i < getLengthFlame(map, Direction.LEFT, sizeFlame)){
            flameUp.add(new Flame(t, ))
        }
        for (int i = 0; i < getLengthFlame(map, Direction.RIGHT, sizeFlame)){
            flameUp.add(new Flame(t, ))
        }
    }

    // xet chieu dai lua co  the render
    public int getLengthFlame(Map map , int direction , int sizeFlame){
        int pos = getPositionAtMap(map);
        int col = map.getColumn();
        switch (direction){
            case Direction.UP:{ // tinh tu vi tri bom di ra
                for (int i = 0; i <= sizeFlame; i++){
                    if (!(map.getMap().get(pos - i * col ) instanceof Grass)){
                        return i + 1;
                    }
                }
                return sizeFlame + 1;
                break;
            }
            case Direction.DOWN:{
                for (int i = 1; i<= sizeFlame; i++){
                    if (!(map.getMap().get(pos + i * col ) instanceof Grass)){
                        return i - 1 ;
                    }
                }
                return sizeFlame;
                break;
            }
            case Direction.LEFT:{
                for (int i = 1; i <= sizeFlame; i++){
                    if (!(map.getMap().get(pos - i) instanceof Grass)){
                        return i - 1;
                    }
                }
                return sizeFlame;
                break;
            }
            case Direction.RIGHT:{
                for (int i = 1; i<= sizeFlame; i++){
                    if (!(map.getMap().get(pos + i ) instanceof Grass)){
                        return i -1 ;
                    }
                }
                return sizeFlame;
                break;
            }
        }
    }
    @Override
    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        switch (state) {
            case PLACED: {
                if (stateTime > timeToExplode) {
                    state = BombState.BURNING;
                    stateTime = 0;
                }
                currentFrame = animation.getKeyFrame(stateTime, false);
                batch.draw(currentFrame, pos.x, pos.y);
                break;
            }
            case BURNING: {
                if (stateTime > timeToExplode) {
                    state = BombState.DISAPPEARED;
                    stateTime = 0;
                }
                // render flame here
                for (Flame a: flameUp){
                    a.render(batch);
                }
                for (Flame a: flameDown){
                    a.render(batch);
                }
                for (Flame a: flameLeft){
                    a.render(batch);
                }
                for (Flame a: flameRight){
                    a.render(batch);
                }
                break;
            }
            case DISAPPEARED: {
                isExploded = true;
                break;
            }
        }
    }

    public boolean isExploded() {
        return isExploded;
    }
}
