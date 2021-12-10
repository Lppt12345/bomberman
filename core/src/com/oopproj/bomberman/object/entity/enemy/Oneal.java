package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.oopproj.bomberman.object.entity.Bomber;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;

public class Oneal extends Enemy {
    private final float time = 0.5f;

    public Oneal(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 100;
        score = 300;
        randomDir();
    }

    private float dTime = time;

    @Override
    public void move(Map map) {
        dTime -= Gdx.graphics.getDeltaTime();
        if (dTime <= 0 || !checkMove(map, lastDirection)) {
            currentDirection = checkDir(map);
        }
        super.move(map);
        if (dTime <= 0) {
            dTime = time;
        }
    }

    public double distance(Map map, int direction) {
        Rectangle obj = nextRec(direction);
        Bomber bomber = map.getPlayer();
        return Math.sqrt(Math.pow(obj.x - bomber.getPos().x, 2) + Math.pow(obj.y - bomber.getPos().y, 2));
    }

    public int checkDir(Map map) {
        Double min = Double.MAX_VALUE;
        int dir = Direction.NOTMOVE;
        if (checkMove(map, Direction.UP)) { // check o tren
            if (distance(map, Direction.UP) < min) {
                min = distance(map, Direction.UP);
                dir = Direction.UP;
            }
        }
        if (checkMove(map, Direction.DOWN)) { // check o duoi
            if (distance(map, Direction.DOWN) < min) {
                min = distance(map, Direction.DOWN);
                dir = Direction.DOWN;
            }
        }
        if (checkMove(map, Direction.LEFT)) { // check ben trai
            if (distance(map, Direction.LEFT) < min) {
                min = distance(map, Direction.LEFT);
                dir = Direction.LEFT;
            }
        }
        if (checkMove(map, Direction.RIGHT)) { // check ben phai
            if (distance(map, Direction.RIGHT) < min) {
                dir = Direction.RIGHT;
            }
        }
        return dir;
    }
}
