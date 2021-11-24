package com.oopproj.bomberman.data;

import com.oopproj.bomberman.object.GameObject;
import com.oopproj.bomberman.object.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public static List<GameObject> map = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public Bomber player;
    public static int row;
    public static int column;
    public int level = 1;
}
