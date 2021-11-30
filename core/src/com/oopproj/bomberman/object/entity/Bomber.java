package com.oopproj.bomberman.object.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.oopproj.bomberman.data.Assets;
import com.oopproj.bomberman.data.Direction;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.object.item.Bomb;

import java.util.Iterator;
import java.util.List;

public class Bomber extends Entity {

    protected List<Bomb> bombList;
    protected int bomRate = 1;
    protected int speed = 200;
//    protected int
    private Assets assets;
    public Bomber(Texture texture, int numberOfFrame, float x, float y) {
        super(texture, numberOfFrame, x, y);
        movingSpeed = 200;
    }


    public void placeBomB (float x , float y){
        if (isAlive){
            Bomb bomb = new Bomb(assets.get(Assets.BOMB) , x,y);
            bombList.add(bomb);
        }
    }

    /**
     * Xoa bom neu no qua gio
     */
    public void destroyBomb(){
        for (Iterator<Bomb> iter = bombList.iterator(); iter.hasNext(); ) {
            Bomb bomb = iter.next();
            if(bomb.getTimeToExplore() < 0) iter.remove();
        }
    }


    @Override
    public void move(Map map) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentDirection = Direction.UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentDirection = Direction.DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentDirection = Direction.LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentDirection = Direction.RIGHT;
        } else {
            currentDirection = Direction.NOTMOVE;
        }
        super.move(map);
    }


}
