package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.entity.Bomber;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;

import java.util.Stack;

public class Doll extends Enemy {
    public Doll(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 100;
        currentDirection = Direction.LEFT;
    }

    public void randomDir() {
        currentDirection = MathUtils.random(0, 3);
    }

    @Override
    public void move(Map map) {
        Bomber bomber = map.getPlayer();
        double dist = Math.sqrt(Math.pow(this.pos.x - bomber.getPos().x, 2) + Math.pow(this.pos.y - bomber.getPos().y, 2));
        if (dist <= 256) {

        } else {
            if (!checkMove(map, lastDirection)) {
                randomDir();
            }
            super.move(map);
        }
    }

    public Direction getDirection(Map map) {
        Bomber bomber = map.getPlayer();
        GameObject[][] surround = new GameObject[5][5];
        int r = 0;
        int c = 0;
        int currentIndex = this.getPositionAtMap(map);
        for (int i = -2; i <= 2; i++) {
            if (i < 0) {
                for (int j = currentIndex + map.getColumn() * i + 2; j <= currentIndex + map.getColumn() * i - 2; j++) {
                    if (j < 0 || j >= map.getMap().size()) {
                        surround[r][c] = null;
                        c++;
                    } else {
                        surround[r][c] = map.getMap().get(j);
                        c++;
                    }
                }
            } else if (i > 0) {
                for (int j = currentIndex + map.getColumn() * i - 2; j <= currentIndex + map.getColumn() * i + 2; j++) {
                    if (j < 0 || j >= map.getMap().size()) {
                        surround[r][c] = null;
                        c++;
                    } else {
                        surround[r][c] = map.getMap().get(j);
                        c++;
                    }
                }
            } else {
                for (int j = currentIndex - 2; j <= currentIndex + 2; j++) {
                    if (j < 0 || j >= map.getMap().size()) {
                        surround[r][c] = null;
                        c++;
                    } else {
                        surround[r][c] = map.getMap().get(j);
                        c++;
                    }
                }
            }
            c = 0;
            r++;
        }

        return null;
    }

    private void getPath(int from, int to, GameObject[][] map, Stack<GameObject> path) {

    }
}
