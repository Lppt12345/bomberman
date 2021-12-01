package com.oopproj.bomberman.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oopproj.bomberman.data.Map;
import com.oopproj.bomberman.data.State;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.entity.Bomber;
import com.oopproj.bomberman.object.entity.Entity;
import com.oopproj.bomberman.object.entity.enemy.Enemy;
import com.oopproj.bomberman.object.ground.Brick;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.object.item.Bomb;
import com.oopproj.bomberman.object.item.Flame;
import com.oopproj.bomberman.ui.ScreenRes;

import java.util.Iterator;
import java.util.List;

public class Gameplay implements Screen {
    private BombermanGame game;
    private Bomber player;
    private Map map;
    private List<Enemy> enemyList;
    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;
    private OrthographicCamera camera;
    private State state;

    public Gameplay(BombermanGame game) throws Exception {
        this.game = game;
        state = State.FADEIN;
        map = new Map("maptest.txt", game.assets);
        WORLD_WIDTH = map.getColumn() * ScreenRes.scale;
        WORLD_HEIGHT = map.getRow() * ScreenRes.scale;
        player = map.getPlayer();
        enemyList = map.getEnemies();
        camera = new OrthographicCamera(700 * ScreenRes.getRatio(), 700);
        game.sound.playLevel1();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (state == State.FADEIN) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha + Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 1) {state = State.STATIC;}
        }
        if (state == State.FADEOUT) {
            game.renderAlpha = MathUtils.clamp(game.renderAlpha - Gdx.graphics.getDeltaTime(), 0, 1);
            if (game.renderAlpha == 0) {state = State.DISAPPEARED;}
        }

        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.setColor(1, 1, 1, game.renderAlpha);

        camera.position.set(
                MathUtils.clamp(player.getPos().x, camera.viewportWidth / 2f, WORLD_WIDTH - camera.viewportWidth / 2f),
                MathUtils.clamp(player.getPos().y, camera.viewportHeight / 2f, WORLD_HEIGHT - camera.viewportHeight / 2f),
                0
        );
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        player.move(map);
        for (Enemy a : enemyList){
            a.move(map);
        }
        updateMap(map);
        game.batch.begin();
        map.render(game.batch);
        player.render(game.batch);
        for (Enemy a : enemyList){
            a.render(game.batch);
        }
        game.batch.end();
    }
    // Cap nhat map lien tuc
    public void updateMap(Map map){
        // Xoa enemy neu no cham lua
        for (Iterator <Enemy> iter = map.getEnemies().iterator(); iter.hasNext();){
            Enemy enemy = iter.next();
            if (enemy.collisonWithFlame(map)){
                enemy.setState(Entity.EntityState.DEAD);
                iter.remove();
            }
        }
        // Khi no chay thi check va cham brick
        for (Bomb bomb : map.getPlayer().getBombList()){
            if (bomb.getState() == Bomb.BombState.BURNING) {
               bomb.checkCollisionWithBrick(map);
            }
        }
        // Xem gach bi xoa chua
        for (Iterator <GameObject> iter = map.getMap().iterator(); iter.hasNext();){
            GameObject obj = iter.next();
            if (obj instanceof Brick){
                Brick tmp = (Brick) obj;
                if (tmp.getState() == Brick.BrickState.DESTROYED){
                    Texture texture = new Texture(Gdx.files.internal("grass.png"));
                    Grass grass = new Grass(texture , tmp.getPos().x , tmp.getPos().y);
                    map.getMap().set(grass.getPositionAtMap(map) , grass);
                }
            }
        }
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
