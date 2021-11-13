package com.oopproj.bomberman.data;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oopproj.bomberman.object.Brick;
import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.Wall;
import com.oopproj.bomberman.object.entity.Bomber;
import com.oopproj.bomberman.object.entity.Creep;
import com.oopproj.bomberman.object.entity.Entity;

import javax.swing.text.TabExpander;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private GameObject[][] map;
    private ArrayList<Entity> enemy;
    int col;
    int row;

    private Bomber bman;
    private int numberOfBrick;

    public Map(String mapPath, Assets assets) throws FileNotFoundException {
        File file = new File(mapPath);
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            input.add(line);
        }
        scan.close();
        row = input.size();
        col = input.get(0).length();
        map = new GameObject[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch (input.get(i).charAt(j)) {
                    case '#': {
                        Texture texture = assets.get(Assets.Obj.WALL);
                        map[i][j] = new Wall(texture, (row - 1 - i) * texture.getWidth(), (col - 1 - j) * texture.getHeight());
                        break;
                    }
                    case '*': {
                        Texture texture = assets.get(Assets.Obj.BRICK);
                        map[i][j] = new Brick(texture, (row - 1 - i) * texture.getWidth(), (col - 1 - j) * texture.getHeight());
                        break;
                    }
                    case 'p': {
                        Texture texture = assets.get(Assets.Obj.BMAN);
                        map[i][j] = new Bomber(texture, 8, (row - 1 - i) * 64, (col - 1 - j) * 64);
                        bman = (Bomber) map[i][j];
                        break;
                    }
                    case '1': {
                        Texture texture = assets.get(Assets.Obj.CREEP);
                        map[i][j] = new Creep(texture, 6, (row - 1 - i) * 64, (col - 1 - j) * 64);
                        enemy.add((Entity) map[i][j]);
                        break;
                    }
                }
            }
        }
    }

//    public GameObject get(int x, int y) {
//        return map[row - 1 - x][col - 1 -y];
//    }

    public Bomber getPlayer() {
        return this.bman;
    }

    public ArrayList<Entity> getEnemy() {
        return enemy;
    }

    public int getNumberOfBrick() {
        return this.numberOfBrick;
    }

    public int getWorldWidth() {
        return col * 64;
    }

    public int getWorldHeight() {
        return row * 64;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; i < col; j++) {
                map[i][j].render(batch);
            }
        }
    }
}
