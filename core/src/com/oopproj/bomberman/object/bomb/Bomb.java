package com.oopproj.bomberman.object.bomb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.ground.Brick;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.ui.GameSound;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.utils.Assets;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends GameObject {
    int secondCounter = 0;
    private float timeToExplode = 3;
    private boolean isExploded = false;
    private List<Flame> flames = new ArrayList<>();
    private int flameUpLength;
    private int flameDownLength;
    private int flameLeftLength;
    private int flameRightLength;
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
        setLengthFlame(map, sizeFlame);
    }

    public BombState getState() {
        return state;
    }

    public void setState(BombState state) {
        this.state = state;
        stateTime = 0;
    }

    public List<Flame> getFlames() {
        return flames;
    }

    public void setFlames(List<Flame> flames) {
        this.flames = flames;
    }

    public double getTimeToExplore() {
        return timeToExplode;
    }

    public void setTimeToExplore(float timeToExplore) {
        this.timeToExplode = timeToExplore;
    }

    /**
     * Hàm đặt chiều dài cho lửa sau khi tìm được chiều dài thực tế.
     *
     * @param map       Map đối tượng tĩnh.
     * @param sizeFlame độ dài ngọn lửa nếu MAX.
     */
    public void setLengthFlame(Map map, int sizeFlame) {
        Texture t = assets.get(Assets.FLAME);
        flameUpLength = findLengthFlame(map, Direction.UP, sizeFlame);
        flameDownLength = findLengthFlame(map, Direction.DOWN, sizeFlame);
        flameLeftLength = findLengthFlame(map, Direction.LEFT, sizeFlame);
        flameRightLength = findLengthFlame(map, Direction.RIGHT, sizeFlame);
        for (int i = 0; i <= flameUpLength; i++) {
            flames.add(new Flame(t, pos.x, pos.y + ScreenRes.scale * i));
        }
        for (int i = 0; i < flameDownLength; i++) {
            flames.add(new Flame(t, pos.x, pos.y - ScreenRes.scale * (i + 1)));
        }
        for (int i = 0; i < flameLeftLength; i++) {
            flames.add(new Flame(t, pos.x - ScreenRes.scale * (i + 1), pos.y));
        }
        for (int i = 0; i < flameRightLength; i++) {
            flames.add(new Flame(t, pos.x + ScreenRes.scale * (i + 1), pos.y));
        }
    }

    /**
     * Tìm chiều dài thực tế của lửa theo 4 hướng.
     *
     * @param map       Map đối tượng tĩnh.
     * @param direction hướng của ngọn lửa.
     * @param sizeFlame độ dài ngọn lửa nếu MAX.
     * @return độ dài ngọn lửa thực tế.
     */
    public int findLengthFlame(Map map, int direction, int sizeFlame) {
        int pos = getPositionAtMap(map);
        int col = map.getColumn();
        switch (direction) {
            case Direction.UP: { // tinh tu vi tri bom di ra
                for (int i = 1; i <= sizeFlame; i++) {
                    if (!(map.getMap().get(pos - i * col) instanceof Grass)) {
                        return i - 1;
                    }
                }
                return sizeFlame;
            }
            case Direction.DOWN: {
                for (int i = 1; i <= sizeFlame; i++) {
                    if (!(map.getMap().get(pos + i * col) instanceof Grass)) {
                        return i - 1;
                    }
                }
                return sizeFlame;
            }
            case Direction.LEFT: {
                for (int i = 1; i <= sizeFlame; i++) {
                    if (!(map.getMap().get(pos - i) instanceof Grass)) {
                        return i - 1;
                    }
                }
                return sizeFlame;
            }
            case Direction.RIGHT: {
                for (int i = 1; i <= sizeFlame; i++) {
                    if (!(map.getMap().get(pos + i) instanceof Grass)) {
                        return i - 1;
                    }
                }
                return sizeFlame;
            }
        }
        return sizeFlame;
    }

    /**
     * Kiểm tra lửa của bom va chạm với tường.
     * @param map Map ánh xạ.
     */
    public void checkCollisionWithBrick(Map map) {
        int col = map.getColumn();
        if ((map.getMap().get(getPositionAtMap(map) - col * (flameUpLength + 1)) instanceof Brick)
                && map.getPlayer().getFlameLength() > flameUpLength) {
            ((Brick) map.getMap().get(getPositionAtMap(map) - col * (flameUpLength + 1))).setState(Brick.BrickState.DESTROYED);
        }
        if ((map.getMap().get(getPositionAtMap(map) + col * (flameDownLength + 1)) instanceof Brick)
                && map.getPlayer().getFlameLength() > flameDownLength) {
            ((Brick) map.getMap().get(getPositionAtMap(map) + col * (flameDownLength + 1))).setState(Brick.BrickState.DESTROYED);
        }
        if ((map.getMap().get(getPositionAtMap(map) - (flameLeftLength + 1)) instanceof Brick)
                && map.getPlayer().getFlameLength() > flameLeftLength) {
            ((Brick) map.getMap().get(getPositionAtMap(map) - (flameLeftLength + 1))).setState(Brick.BrickState.DESTROYED);
        }
        if ((map.getMap().get(getPositionAtMap(map) + (flameRightLength + 1)) instanceof Brick)
                && map.getPlayer().getFlameLength() > flameRightLength) {
            ((Brick) map.getMap().get(getPositionAtMap(map) + (flameRightLength + 1))).setState(Brick.BrickState.DESTROYED);
        }
    }

    /**
     * Kiểm tra bomb va chạm với lửa.
     * @param map Map ánh xạ.
     */
    public void collisionWithFlame(Map map) {
        for (Bomb bomb : map.getPlayer().getBombList()) {
            if (bomb.getState() == Bomb.BombState.BURNING) {
                for (Flame flame : bomb.getFlames()) {
                    if (getPos().overlaps(flame.getPos()) && getState() == BombState.PLACED) {
                        setState(BombState.BURNING);
                    }
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        switch (state) {
            case PLACED: {
                if (stateTime > secondCounter) {
                    GameSound.playBombTick();
                    secondCounter++;
                }
                if (stateTime > timeToExplode) {
                    GameSound.playExplosion();
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
                for (Flame a : flames) {
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

    public enum BombState {
        PLACED, BURNING, DISAPPEARED
    }
}
