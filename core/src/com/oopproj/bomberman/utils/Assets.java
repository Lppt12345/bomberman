package com.oopproj.bomberman.utils;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Assets {
    public static final int BMAN = 0;
    public static final int CREEP = 1;
    public static final int SPEED_CREEP = 2;
    public static final int ONEAL = 3;
    public static final int GRASS = 4;
    public static final int WALL = 5;
    public static final int BRICK = 6;
    public static final int PORTAL = 7;
    public static final int BOMB = 8;
    public static final int FLAME = 9;
    public static final int BOMB_ITEM = 10;
    public static final int FLAME_ITEM = 11;
    public static final int SPEED_ITEM = 12;
    public static final int HEART_ITEM = 13;
    public static final int DOLL = 14;
    private static Assets assets = null;
    private ArrayList<Texture> textures;

    private Assets() {
        textures = new ArrayList<>();
        textures.add(new Texture("objects/bman.png"));
        textures.add(new Texture("objects/creep.png"));
        textures.add(new Texture("objects/speed_creep.png"));
        textures.add(new Texture("objects/oneal.png"));
        textures.add(new Texture("objects/grass.png"));
        textures.add(new Texture("objects/wall.png"));
        textures.add(new Texture("objects/brick.png"));
        textures.add(new Texture("objects/portal.png"));
        textures.add(new Texture("objects/bomb.png"));
        textures.add(new Texture("objects/flame.png"));
        textures.add(new Texture("objects/bombitem.png"));
        textures.add(new Texture("objects/flameitem.png"));
        textures.add(new Texture("objects/speeditem.png"));
        textures.add(new Texture("objects/heartitem.png"));
        textures.add(new Texture("objects/doll.png"));
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
