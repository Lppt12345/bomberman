package com.oopproj.bomberman.object.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.object.entity.algorithm.astar.AStar;
import com.oopproj.bomberman.object.entity.algorithm.astar.Square;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;

import java.util.Iterator;
import java.util.Stack;

public class Demon extends Enemy {
    private final float time = 6;
    AStar aStar;
    Stack<Square> moves;
    private float dTime = time;

    public Demon(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 150;
        score = 200;
        randomDir();
    }

    @Override
    public void move(Map map) {
        dTime -= Gdx.graphics.getDeltaTime();
        if (dTime <= 0 || !checkMove(map, lastDirection)) {
            aStar = new AStar(new Square(map, getPositionAtMap(map), map.getPlayer().getPositionAtMap(map)));
            for (Iterator<Square> iter = aStar.getStack().iterator(); iter.hasNext(); ) {
                Square tmp = iter.next();
                System.out.println(tmp.getPosition());
//                currentDirection = checkDir(tmp , map);
//                super.move(map);
//                if (map.getMap().get(tmp.getPosition()).getPos().contains(getPos())){
//                    iter.remove();
//                }
            }
            System.out.println("END?????????");
        }
//        super.move(map);
        if (dTime <= 0) {
            dTime = time;
        }
    }

    public int checkDir(Square square, Map map) {
        int pos = square.getPosition();
        if (this.getPositionAtMap(map) - map.getColumn() == pos) {
            return Direction.UP;
        }
        if (this.getPositionAtMap(map) + map.getColumn() == pos) {
            return Direction.DOWN;
        }
        if (this.getPositionAtMap(map) - 1 == pos) {
            return Direction.LEFT;
        }
        if (this.getPositionAtMap(map) + 1 == pos) {
            return Direction.RIGHT;
        }
        return Direction.NOTMOVE;
    }
}
