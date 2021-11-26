package com.oopproj.bomberman.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.entity.*;
import com.oopproj.bomberman.object.entity.enemy.Creep;
import com.oopproj.bomberman.object.entity.enemy.Enemy;
import com.oopproj.bomberman.object.entity.enemy.Oneal;
import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.object.ground.Wall;
import com.oopproj.bomberman.ui.ScreenRes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<GameObject> map = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Bomber player;
    private static int row;
    private static int column;
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

    public static int getRow() {
        return row;
    }

    public static void setRow(int row) {
        Map.row = row;
    }

    public static int getColumn() {
        return column;
    }

    public static void setColumn(int column) {
        Map.column = column;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
                int colM = (column - 1 - j) * Scale;
                switch (check) {
                    case 'p':
                        // Need constructor
                        player = new Bomber(assets.get(Assets.BMAN), 8, rowM, colM);
                        obj = new Grass(assets.get(Assets.GRASS), rowM, colM);
                        break;
                    case '#':
                        obj = new Wall(assets.get(Assets.WALL), rowM, colM);
                        break;
                    case '1':
                        enemy = new Creep(assets.get(Assets.CREEP), 6, rowM, colM);
                        obj = new Grass(assets.get(Assets.GRASS), rowM, colM);
                        break;
                    case '2':
                        enemy = new Oneal(assets.get(Assets.CREEP), 6, rowM, colM);
                        obj = new Grass(assets.get(Assets.GRASS), rowM, colM);
                        break;
                    case '*':
                        obj = new Grass(assets.get(Assets.BRICK), rowM, colM);
                        break;
                    default:
                        obj = new Grass(assets.get(Assets.GRASS), rowM, colM);
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
