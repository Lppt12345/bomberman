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

    @Override
    public int calculateDir() {
        return calculateDir(enemy.getLastDirection());
    }

    public int calculateDir(int ramdom){
        if (ramdom == Direction.UP || ramdom == Direction.DOWN){
            return calculateCol(ramdom);
        }
        if (ramdom == Direction.LEFT || ramdom == Direction.RIGHT){
            return calculateRow(ramdom);
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
