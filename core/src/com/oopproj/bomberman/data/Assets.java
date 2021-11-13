package com.oopproj.bomberman.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public enum Obj {
        BMAN, CREEP,
        GRASS, WALL, BRICK,
        PORTAL,
        BOMB, FLAME,
        BOMB_ITEM, FLAME_ITEM, SPEED_ITEM
    }

    private AssetManager manager;

    public Assets() {
        manager = new AssetManager();
        manager.load("bman_47x86.png", Texture.class);
        manager.load("bomb_48x48.png", Texture.class);
        manager.load("bomb_item_32x32.png", Texture.class);
        manager.load("brick_64x64.png", Texture.class);
        manager.load("creep_50x50", Texture.class);
        manager.load("flame_48x48.png", Texture.class);
        manager.load("flame_item_32x32.png", Texture.class);
        manager.load("grass_64x64.png", Texture.class);
        manager.load("portal_64x64.png",Texture.class);
        manager.load("speed_item_32x32.png", Texture.class);
        manager.load("wall_64x64.png", Texture.class);
    }

    public boolean update() {
        return manager.update();
    }

    public float getProgress() {
        return manager.getProgress();
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    public Texture get(Obj o) {
        switch (o) {
            case BMAN: {
                return manager.get("bman_47x86.png", Texture.class);
            }
            case CREEP: {
                return manager.get("creep_50x50", Texture.class);
            }
            case GRASS: {
                return manager.get("grass_64x64.png", Texture.class);
            }
            case WALL: {
                return manager.get("wall_64x64.png", Texture.class);
            }
            case BRICK: {
                return manager.get("brick_64x64.png", Texture.class);
            }
            case PORTAL: {
                return manager.get("portal_64x64.png",Texture.class);
            }
            case BOMB: {
                return manager.get("bomb_48x48.png", Texture.class);
            }
            case FLAME: {
                return manager.get("flame_48x48.png", Texture.class);
            }
            case BOMB_ITEM: {
                return manager.get("bomb_item_32x32.png", Texture.class);
            }
            case FLAME_ITEM: {
                return manager.get("flame_item_32x32.png", Texture.class);
            }
            case SPEED_ITEM: {
                return manager.get("speed_item_32x32.png", Texture.class);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Assets assets = new Assets();
        System.out.println("Loading...");
        assets.finishLoading();
        System.out.println("Done");
    }
}
