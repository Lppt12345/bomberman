package com.oopproj.bomberman.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.entity.*;
import com.oopproj.bomberman.object.entity.enemy.Creep;
import com.oopproj.bomberman.object.entity.enemy.Enemy;
import com.oopproj.bomberman.object.entity.enemy.Oneal;
import com.oopproj.bomberman.object.entity.enemy.SpeedCreep;
import com.oopproj.bomberman.object.ground.Brick;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.object.ground.Wall;
import com.oopproj.bomberman.object.item.BombItem;
import com.oopproj.bomberman.object.item.FlameItem;
import com.oopproj.bomberman.object.item.Item;
import com.oopproj.bomberman.object.item.SpeedItem;
import com.oopproj.bomberman.ui.ScreenRes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<GameObject> map = new ArrayList<>(); // nhung doi tuong tinh
    private List<Enemy> enemies = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Bomber player;
    private int row;
    private int column;
    public int level;
    public int Scale = ScreenRes.scale;

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

    public void setPlayer(Bomber player) {
        this.player = player;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
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

    /**
     * Tao map dươc lay tu file txt
     */
    public Map(String mapFile, Assets assets) throws Exception {
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
                        enemy = new Oneal(assets.get(Assets.CREEP), 6, colM, rowM);
                        obj = new Grass(assets.get(Assets.GRASS), colM, rowM);
                        break;
                    case '3':
                        enemy = new SpeedCreep(assets.get(Assets.CREEP), 6, colM, rowM);
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
                        items.add(new SpeedItem(assets.get(Assets.PORTAL), colM, rowM));
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

    public void render(final SpriteBatch batch) {
        for (GameObject o : map) {
            o.render(batch);
        }
    }
}
