package com.oopproj.bomberman.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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

    private ArrayList<Texture> textures;

    public Assets() {
        textures = new ArrayList<>();
        textures.add(new Texture("bman.png"));
        textures.add(new Texture("creep.png"));
        textures.add(new Texture("grass.png"));
        textures.add(new Texture("wall.png"));
        textures.add(new Texture("brick.png"));
        textures.add(new Texture("portal.png"));
        textures.add(new Texture("bomb.png"));
        textures.add(new Texture("flame.png"));
        textures.add(new Texture("bombitem.png"));
        textures.add(new Texture("flameitem.png"));
        textures.add(new Texture("speeditem.png"));
    }

    public Texture get(int object) {
        return textures.get(object);
    }
}
