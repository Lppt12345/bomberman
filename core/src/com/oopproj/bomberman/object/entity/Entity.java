package com.oopproj.bomberman.object.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.object.item.Bomb;
import com.oopproj.bomberman.object.item.Flame;


public abstract class Entity extends GameObject {
    public enum EntityState {
        PROTECTED, ALIVE, DEAD, MOVING, BURNING
    }

    protected TextureRegion[][] frame;
    protected TextureRegion currentFrame;
    protected Animation[] animation;
    protected float animationSpeed = 0.05f;
    protected float movingSpeed;
    protected float stateTime = 0f;
    protected int currentDirection;
    protected int lastDirection = Direction.DOWN;
    protected EntityState state;

    public Entity(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, x, y);
        pos.width = (float) texture.getWidth() / numberOfFrame;
        pos.height = (float) texture.getWidth() / numberOfFrame;
        frame = TextureRegion.split(texture,
                texture.getWidth() / numberOfFrame,
                texture.getHeight() / 4);
        animation = new Animation[4];
        for (int i = 0; i < 4; i++) {
            animation[i] = new Animation<TextureRegion>(animationSpeed, frame[i]);
        }
        this.state = EntityState.ALIVE;
    }

    public void setMovingSpeed(float speed) {
        this.movingSpeed = speed;
    }

    public void setAnimationSpeed(float speed) {
        this.animationSpeed = speed;
    }

    /**
     * Hàm kiểm tra xem có di chuyển được hay không
     *
     * @param map       Đối tượng map ánh xạ
     * @param direction Hướng di chuyển
     * @return Nếu di chuyển được thì trả về true và ngược lại trả về false
     */
    public boolean checkMove(Map map, int direction) {
        int posAtMap = getPositionAtMap(map);
        Rectangle entity = null;
        GameObject tmp1 = null, tmp2 = null;
        int col = map.getColumn();
        switch (direction) {
            case Direction.UP: {
                entity = getRec(direction);
                if (posAtMap - col < 0) {
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap - col);
                tmp2 = map.getMap().get(posAtMap - col + 1);
                if (!(tmp1 instanceof Grass) && entity.overlaps(tmp1.getPos())) {
                    return false;
                }
                if (!(tmp2 instanceof Grass) && entity.overlaps(tmp2.getPos())) {
                    return false;
                }
                break;
            }
            case Direction.DOWN: {
                entity = getRec(direction);
                if (posAtMap + col + 1 > map.getMap().size()) {
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap + col);
                tmp2 = map.getMap().get(posAtMap + col + 1);

                if (!(tmp1 instanceof Grass) && entity.overlaps(tmp1.getPos())) {
                    return false;
                }
                if (!(tmp2 instanceof Grass) && entity.overlaps(tmp2.getPos())) {
                    return false;
                }
                break;
            }
            case Direction.LEFT: {
                entity = getRec(direction);
                if (posAtMap - 1 - col < 0) {
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap - 1);
                tmp2 = map.getMap().get(posAtMap - 1 - col);

                if (!(tmp1 instanceof Grass) && entity.overlaps(tmp1.getPos())) {
                    return false;
                }
                if (!(tmp2 instanceof Grass) && entity.overlaps(tmp2.getPos())) {
                    return false;
                }
                break;
            }
            case Direction.RIGHT: {
                entity = getRec(direction);
                if (posAtMap + 1 - col < 0) {
                    return false;
                }
                tmp1 = map.getMap().get(posAtMap + 1);
                tmp2 = map.getMap().get(posAtMap + 1 - col);

                if (!(tmp1 instanceof Grass) && entity.overlaps(tmp1.getPos())) {
                    return false;
                }
                if (!(tmp2 instanceof Grass) && entity.overlaps(tmp2.getPos())) {
                    return false;
                }
                break;
            }
        }

        // Check va cham vs bom
        for (Bomb bomb : map.getPlayer().bombList) {
            if (!bomb.getPos().overlaps(getPos())) {
                Rectangle tmp = getRec(direction);
                if (tmp.overlaps(bomb.getPos())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Hàm xác định khối chữ nhật tiếp theo tùy vào hướng di chuyển
     *
     * @param direction Hướng di chuyển
     * @return
     */
    public Rectangle getRec(int direction) {
        switch (direction) {
            case Direction.UP: {
                return new Rectangle(pos.x, pos.y + movingSpeed * Gdx.graphics.getDeltaTime(), pos.width, pos.height);
            }
            case Direction.DOWN: {
                return new Rectangle(pos.x, pos.y - movingSpeed * Gdx.graphics.getDeltaTime(), pos.width, pos.height);
            }
            case Direction.LEFT: {
                return new Rectangle(pos.x - movingSpeed * Gdx.graphics.getDeltaTime(), pos.y, pos.width, pos.height);
            }
            case Direction.RIGHT: {
                return new Rectangle(pos.x + movingSpeed * Gdx.graphics.getDeltaTime(), pos.y, pos.width, pos.height);
            }
        }
        return null;
    }

    private float currentSpeed = 0;
    private float accelerate = 450;
    public void move(Map map) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) animation[lastDirection].getKeyFrame(stateTime, true);
        if (currentFrame == null) {
            currentFrame = frame[0][0];
        }
        switch (currentDirection) {
            case Direction.UP: {
                currentSpeed = MathUtils.clamp(currentSpeed + accelerate * Gdx.graphics.getDeltaTime(), 0, movingSpeed);
                lastDirection = Direction.UP;
                animation[Direction.UP].setFrameDuration(animationSpeed);
                if (!checkMove(map, lastDirection)) {
                    return;
                }
                pos.y += currentSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.DOWN: {
                currentSpeed = MathUtils.clamp(currentSpeed + accelerate * Gdx.graphics.getDeltaTime(), 0, movingSpeed);
                lastDirection = Direction.DOWN;
                animation[Direction.DOWN].setFrameDuration(animationSpeed);
                if (!checkMove(map, lastDirection)) {
                    return;
                }
                pos.y -= currentSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.LEFT: {
                currentSpeed = MathUtils.clamp(currentSpeed + accelerate * Gdx.graphics.getDeltaTime(), 0, movingSpeed);
                lastDirection = Direction.LEFT;
                animation[Direction.LEFT].setFrameDuration(animationSpeed);
                if (!checkMove(map, lastDirection)) {
                    return;
                }
                pos.x -= currentSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            case Direction.RIGHT: {
                currentSpeed = MathUtils.clamp(currentSpeed + accelerate * Gdx.graphics.getDeltaTime(), 0, movingSpeed);
                lastDirection = Direction.RIGHT;
                animation[Direction.RIGHT].setFrameDuration(animationSpeed);
                if (!checkMove(map, lastDirection)) {
                    return;
                }
                pos.x += currentSpeed * Gdx.graphics.getDeltaTime();
                break;
            }
            default: {
                animation[0].setFrameDuration(0);
                animation[1].setFrameDuration(0);
                animation[2].setFrameDuration(0);
                animation[3].setFrameDuration(0);
                currentFrame = frame[lastDirection][0];
                currentSpeed = MathUtils.clamp(currentSpeed - accelerate * Gdx.graphics.getDeltaTime(), 0, movingSpeed);
                switch (lastDirection) {
                    case Direction.UP: {
                        if (!checkMove(map, lastDirection)) {
                            return;
                        }
                        pos.y += currentSpeed * Gdx.graphics.getDeltaTime();
                        break;
                    }
                    case Direction.DOWN: {
                        if (!checkMove(map, lastDirection)) {
                            return;
                        }
                        pos.y -= currentSpeed * Gdx.graphics.getDeltaTime();
                        break;
                    }
                    case Direction.LEFT: {
                        if (!checkMove(map, lastDirection)) {
                            return;
                        }
                        pos.x -= currentSpeed * Gdx.graphics.getDeltaTime();
                        break;
                    }
                    case Direction.RIGHT: {
                        if (!checkMove(map, lastDirection)) {
                            return;
                        }
                        pos.x += currentSpeed * Gdx.graphics.getDeltaTime();
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * Check va chạm với lửa
     *
     * @param map Map ánh xạ
     * @return nếu va chạm thì true không thì flase
     */
    public boolean collisionWithFlame(Map map) {
        for (Bomb bomb : map.getPlayer().bombList) {
            if (bomb.getState() == Bomb.BombState.BURNING) {
                for (Flame flame : bomb.getFlames()) {
//                    Rectangle entity = new Rectangle(pos.x, pos.y, pos.width, pos.height);
                    if (getPos().overlaps(flame.getPos())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public int getLastDirection() {
        return lastDirection;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    public void render(SpriteBatch batch) {
        batch.draw(currentFrame, pos.x, pos.y);
    }


}
