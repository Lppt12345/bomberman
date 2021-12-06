package com.oopproj.bomberman.object.entity.ai;

import com.oopproj.bomberman.utils.Map;
import com.oopproj.bomberman.object.entity.enemy.Enemy;

public abstract class AI {
    Map map;
    Enemy enemy;

    public AI(Map map, Enemy enemy) {
        this.map = map;
        this.enemy = enemy;
    }

    public Map getMap() {
        return map;
    }


    public Enemy getEnemy() {
        return enemy;
    }


    /**
     * Tinh toan huong di
     * @return huong di sau khi tinh toan
     */
    public abstract int calculateDir(Map map ,Enemy enemy);
}
