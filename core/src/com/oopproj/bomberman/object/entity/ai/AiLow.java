package com.oopproj.bomberman.object.entity.ai;

import com.badlogic.gdx.math.MathUtils;
import com.oopproj.bomberman.utils.Direction;
import com.oopproj.bomberman.utils.Map;
import com.oopproj.bomberman.object.entity.enemy.Enemy;

public class AiLow extends AI{
    /**
     * Random huong di
     * @return Random huong di tren duoi trai phai
     */

    public AiLow(Map map, Enemy enemy) {
        super(map, enemy);
    }

    protected int randomDir(int lastDirection){
        int ramdom = MathUtils.random(0,3);
        while (ramdom == lastDirection){
            ramdom = MathUtils.random(0,3);
        }
        return ramdom;
    }

    @Override
    public int calculateDir(Map map , Enemy enemy) {
        int radom = randomDir(enemy.getLastDirection());
        return calculateDir(radom);
    }

    public int calculateDir(int ramdom){
        if (ramdom == Direction.UP || ramdom == Direction.DOWN){
            return calculateRow(ramdom);
        }
        if (ramdom == Direction.LEFT || ramdom == Direction.RIGHT){
            return calculateCol(ramdom);
        }
        return Direction.NOTMOVE;
    }

    public int calculateRow(int random){
        if (map.getPlayer().getPos().y > enemy.getPos().y){
            return Direction.UP;
        }
        if (map.getPlayer().getPos().y < enemy.getPos().y){
            return Direction.DOWN;
        }
        return Direction.NOTMOVE;
    }
    public int calculateCol(int random){
        if (map.getPlayer().getPos().x > enemy.getPos().x){
            return Direction.RIGHT;
        }
        if (map.getPlayer().getPos().x < enemy.getPos().x){
            return Direction.LEFT;
        }
        return Direction.NOTMOVE;
    }


}
