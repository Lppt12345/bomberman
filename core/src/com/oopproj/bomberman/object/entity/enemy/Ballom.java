package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.entity.Bomber;
import com.oopproj.bomberman.object.entity.algorithm.astar.AStar;
import com.oopproj.bomberman.object.entity.algorithm.astar.Square;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.ui.ScreenRes;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;

public class Ballom extends  Enemy{
    public Ballom(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 100;
        currentDirection = Direction.LEFT;
    }

    public void switchDir() {
        switch (lastDirection) {
            case Direction.UP:
                currentDirection = Direction.DOWN;
                break;
            case Direction.DOWN:
                currentDirection = Direction.UP;
                break;
            case Direction.LEFT:
                currentDirection = Direction.RIGHT;
                break;
            case Direction.RIGHT:
                currentDirection = Direction.LEFT;
                break;
            default:
                break;
        }
    }
    @Override
    public void move(Map map) {
        int pos = getPositionAtMap(map);
        int col = map.getColumn();
        Grass grass = (Grass) map.getMap().get(pos);
        if (this.pos.x == grass.getPos().x + (grass.getPos().width - this.pos.width) / 2
            && this.pos.y == grass.getPos().y + (grass.getPos().height - this.pos.height) / 2) {
            currentDirection = checkDir(map , pos , col);
        }
        super.move(map);
    }
    public double distance(Map map , int pos){
        GameObject obj = map.getMap().get(pos);
        Bomber bomber = map.getPlayer();
        return Math.sqrt(Math.pow(obj.getPos().x - bomber.getPos().x, 2) + Math.pow(obj.getPos().y - bomber.getPos().y, 2));
    }

    public int checkDir(Map map , int pos , int col){
        Double min = Double.MAX_VALUE;
        int dir = Direction.NOTMOVE;
        if (pos - col > 0 && (map.getMap().get(pos -col) instanceof Grass)){ // check o tren
            if (distance(map , pos - col) < min){
                min = distance(map , pos - col);
                dir =  Direction.UP;
            }
        }
        if (pos + col <map.getMap().size() && (map.getMap().get(pos + col) instanceof Grass)){ // check o duoi
            if (distance(map , pos + col) < min){
                min = distance(map , pos + col);
                dir = Direction.DOWN;
            }
        }
        if (pos - 1 > 0 && (map.getMap().get(pos -1) instanceof Grass)){ // check ben trai
            if (distance(map , pos - 1) < min){
                min = distance(map , pos - 1);
                dir = Direction.LEFT;
            }
        }
        if (pos + 1 > 0 && (map.getMap().get(pos + 1) instanceof Grass)){ // check ben phai
            if (distance(map , pos + 1) < min){
                dir = Direction.RIGHT;
            }
        }
        return dir;
    }
}
