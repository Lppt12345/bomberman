package com.oopproj.bomberman.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.bomb.Bomb;
import com.oopproj.bomberman.object.entity.Bomber;
import com.oopproj.bomberman.object.entity.Entity;
import com.oopproj.bomberman.object.entity.enemy.*;
import com.oopproj.bomberman.object.ground.Brick;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.object.ground.Wall;
import com.oopproj.bomberman.object.item.*;
import com.oopproj.bomberman.ui.GameSound;
import com.oopproj.bomberman.ui.ScreenRes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {
    public int level;
    public int Scale = ScreenRes.scale;
    private Assets assets;
    private List<GameObject> map = new ArrayList<>(); // nhung doi tuong tinh
    private List<Enemy> enemies = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Bomber player;
    private int row;
    private long score = 0;
    private int column;

    /**
     * Tao map dươc lay tu file txt
     */
    public Map(String mapFile) throws Exception {
        assets = Assets.getInstance();
        FileReader fis = new FileReader(mapFile);
        BufferedReader br = new BufferedReader(fis);
        String[] line = br.readLine().split(" ");
        level = Integer.parseInt(line[0]);
        row = Integer.parseInt(line[1]);
        column = Integer.parseInt(line[2]);

        for (int i = 0; i < row; i++) {
            String tmp = br.readLine();

            for (int j = 0; j < column; j++) {
                GameObject obj = null;
                Enemy enemy = null;
                char check = tmp.charAt(j);
                // Load các phần tử trong txt vao map va enermy theo vi tri render trong libgdx
                // Chi so cac phan tu duoc danh tu duoi den tren
                int rowM = (row - 1 - i) * Scale;
                int colM = j * Scale;
                switch (check) {
                    case 'p':
                        // Need constructor
                        player = new Bomber(assets.get(Assets.BMAN), 8, colM, rowM);
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                    case '#':
                        obj = new Wall(assets.get(Assets.WALL), colM, rowM);
                        break;
                    case '1':
                        enemy = new Creep(assets.get(Assets.CREEP), 6, colM, rowM);
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                    case '2':
                        enemy = new Doll(assets.get(Assets.CREEP), 6, colM, rowM);
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                    case '3':
                        enemy = new SpeedCreep(assets.get(Assets.SPEED_CREEP), 6, colM, rowM);
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                    case '4':
                        enemy = new Oneal(assets.get(Assets.ONEAL), 6, colM, rowM);
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                    case '5':
                        enemy = new Demon(assets.get(Assets.ONEAL), 6, colM, rowM);
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                    case '*':
                        obj = new Brick(assets.get(Assets.BRICK), colM, rowM);
                        break;
                    case 'b':
                        items.add(new BombItem(assets.get(Assets.BOMB_ITEM), colM, rowM));
                        obj = new Brick(assets.get(Assets.BRICK), colM, rowM);
                        break;
                    case 'f':
                        items.add(new FlameItem(assets.get(Assets.FLAME_ITEM), colM, rowM));
                        obj = new Brick(assets.get(Assets.BRICK), colM, rowM);
                        break;
                    case 's':
                        items.add(new SpeedItem(assets.get(Assets.SPEED_ITEM), colM, rowM));
                        obj = new Brick(assets.get(Assets.BRICK), colM, rowM);
                        break;
                    case 'x':
                        items.add(new Portal(assets.get(Assets.PORTAL), colM, rowM));
                        obj = new Brick(assets.get(Assets.BRICK), colM, rowM);
                        break;
                    default:
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                }
                // Xử lí để add các phần tử vao mang
                if (enemy != null) enemies.add(enemy);
                map.add(obj);
                System.out.print(check);
            }
            System.out.println();
        }
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public List<GameObject> getMap() {
        return map;
    }

    public void setMap(List<GameObject> map) {
        this.map = map;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Bomber getPlayer() {
        return player;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void resetPlayer(Bomber bomber) {
        bomber.setLife(bomber.getLife() - 1);
        if (bomber.getLife() != 0) {
            bomber.resetPlayer(bomber.getLife());
            GameSound.playPlayerDeath();
        } else {
            GameSound.playGameOver();
        }
    }

    // Cap nhat this lien tuc
    public void updateMap() {
        // Xoa enemy neu no cham lua va va cham vs nguoi choi
        for (Iterator<Enemy> iter = enemies.iterator(); iter.hasNext(); ) {
            Enemy enemy = iter.next();
            if (enemy.collisionWithFlame(this)) {
                if (enemy.getState() == Entity.EntityState.ALIVE) {
                    enemy.setState(Entity.EntityState.BURNING);
                    enemy.resetStateTime();
                }
                if (enemy.getState() == Entity.EntityState.DEAD) {
                    score += enemy.getScore();
                    iter.remove();
                }
            }
            if (enemy.getPos().overlaps(player.getPos()) && player.getState() == Entity.EntityState.ALIVE) {
                player.setState(Entity.EntityState.BURNING);
                player.resetStateTime();
            }
        }
        if (player.collisionWithFlame(this) && player.getState() == Entity.EntityState.ALIVE) {
            player.setState(Entity.EntityState.BURNING);
            player.resetStateTime();
        }
        if (player.getState() == Entity.EntityState.DEAD) {
            resetPlayer(player);
        }
        // Khi no chay thi check va cham brick
        for (Bomb bomb : player.getBombList()) {
            if (bomb.getState() == Bomb.BombState.BURNING) {
                bomb.checkCollisionWithBrick(this);
            }
        }
        // Them co vao neu gach bi xoa
        for (Iterator<GameObject> iter = map.iterator(); iter.hasNext(); ) {
            GameObject obj = iter.next();
            if (obj instanceof Brick) {
                Brick tmp = (Brick) obj;
                if (tmp.getState() == Brick.BrickState.DESTROYED) {
                    Texture texture = assets.get(Assets.GRASS);
                    Grass grass = new Grass(texture, tmp.getPos().x, tmp.getPos().y);
                    map.set(grass.getPositionAtMap(this), grass);
                }
            }
        }
        // check an item
        for (Iterator<Item> iter = this.getItems().iterator(); iter.hasNext(); ) {
            Item item = iter.next();
            item.collisionWithBomBer(this);
            if (item.isDestroyed()) {
                iter.remove();
            }
        }
        for (Iterator<Bomb> iter = player.getBombList().iterator(); iter.hasNext(); ) {
            Bomb bomb = iter.next();
            bomb.collisionWithFlame(this);
        }
    }

    public void render(final SpriteBatch batch) {
        for (GameObject o : map) {
            o.render(batch);
        }
    }
}
