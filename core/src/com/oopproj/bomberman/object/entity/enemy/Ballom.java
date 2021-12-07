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
    boolean check = true;
    int position;
    int destination;
    int col;
    public Ballom(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
//        pos.x += (ScreenRes.scale - this.pos.width) / 2;
//        pos.y += (ScreenRes.scale - this.pos.height) / 2;
        movingSpeed = 50;
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
                currentDirection = Direction.RIGHT;
                break;
            default:
                break;
        }
    }
    @Override
    public void move(Map map) {
        if (!pos.overlaps(map.getPlayer().getPos())){
            currentDirection = checkDir(map, getPositionAtMap(map) , map.getColumn(), map.getPlayer().getPositionAtMap(map));
        }
    }
    public double distance(Map map , int pos , int des){
        GameObject obj = map.getMap().get(pos);
        GameObject obj2 = map.getMap().get(des);
        return Math.sqrt(Math.pow(obj.getPos().x - obj2.getPos().x, 2) + Math.pow(obj.getPos().y - obj2.getPos().y, 2));
    }

    public int checkDir(Map map , int pos , int col , int des){
        Double min = Double.MAX_VALUE;
        int dir = Direction.NOTMOVE;
        if (pos - col > 0 && (map.getMap().get(pos -col) instanceof Grass)){ // check o tren
            if (distance(map , pos - col , des) < min){
                min = distance(map , pos - col, des);
                dir =  Direction.UP;
            }
        }
        if (pos + col <map.getMap().size() && (map.getMap().get(pos + col) instanceof Grass)){ // check o duoi
            if (distance(map , pos + col, des) < min){
                min = distance(map , pos + col, des);
                dir = Direction.DOWN;
            }
        }
        if (pos - 1 > 0 && (map.getMap().get(pos -1) instanceof Grass)){ // check ben trai
            if (distance(map , pos - 1, des) < min){
                min = distance(map , pos - 1 , des);
                dir = Direction.LEFT;
            }
        }
        if (pos + 1 > 0 && (map.getMap().get(pos + 1) instanceof Grass)){ // check ben phai
            if (distance(map , pos + 1 , des) < min){
                dir = Direction.RIGHT;
            }
        }
        return dir;
    }
}
