package com.oopproj.bomberman.utils;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Assets {
    public static final int BMAN = 0;
    public static final int CREEP = 1;
    public static final int GRASS = 2;
    public static final int WALL = 3;
    public static final int BRICK = 4;
    public static final int PORTAL = 5;
    public static final int BOMB = 6;
    public static final int FLAME = 7;
    public static final int BOMB_ITEM = 8;
    public static final int FLAME_ITEM = 9;
    public static final int SPEED_ITEM = 10;
    private static Assets assets = null;
    private ArrayList<Texture> textures;

    private Assets() {
        textures = new ArrayList<>();
        textures.add(new Texture("objects/bman.png"));
        textures.add(new Texture("objects/creep.png"));
        textures.add(new Texture("objects/grass.png"));
        textures.add(new Texture("objects/wall.png"));
        textures.add(new Texture("objects/brick.png"));
        textures.add(new Texture("objects/portal.png"));
        textures.add(new Texture("objects/bomb.png"));
        textures.add(new Texture("objects/flame.png"));
        textures.add(new Texture("objects/bombitem.png"));
        textures.add(new Texture("objects/flameitem.png"));
        textures.add(new Texture("objects/speeditem.png"));
    }

    public static Assets getInstance() {
        if (assets == null) {
            assets = new Assets();
        }
        return assets;
    }

    public Texture get(int object) {
        return textures.get(object);
    }
}
