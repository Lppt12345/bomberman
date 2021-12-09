package com.oopproj.bomberman.object.entity.algorithm.astar;

import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.utils.Map;

import java.util.Stack;

public class Square {
    private Map map;
    private int init;           // o bat dau
    private int destination;   // o ket thuc
    private int col;

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Square(Map map, int init, int des) {
        this.map = map;
        col = map.getColumn();
        this.destination = des;
        this.init = init;
    }

    public boolean equals(Object y) {
        if (y instanceof Square) {
            Square other = (Square) y;
            return other.init == init && other.map == map && other.destination == destination;
        } else {
            return false;
        }
    }

    public boolean isGoal() {
        return init == destination;
    }

    /**
     * Tinh khoang cach manhattan
     *
     * @return kc manhattan tinh theo int
     */
    public int manhattan() {
        int colC = (int) Math.abs(map.getMap().get(init).getPos().x - map.getMap().get(destination).getPos().x);
        int rowC = (int) Math.abs(map.getMap().get(init).getPos().y - map.getMap().get(destination).getPos().y);
        return colC + rowC;
    }

    public Iterable<Square> neighbors() {
        Stack<Square> neighB = new Stack<>();
        if (init - col > 0 && (map.getMap().get(init - col) instanceof Grass)) { // check o tren
            neighB.push(new Square(map, init - col, destination));
        }
        if (init + col < map.getMap().size() && (map.getMap().get(init + col) instanceof Grass)) { // check o duoi
            neighB.push(new Square(map, init + col, destination));
        }
        if (init - 1 > 0 && (map.getMap().get(init - 1) instanceof Grass)) { // check ben trai
            neighB.push(new Square(map, init - 1, destination));
        }
        if (init + 1 > 0 && (map.getMap().get(init + 1) instanceof Grass)) { // check ben phai
            neighB.push(new Square(map, init + 1, destination));
        }
        return neighB;
    }


}
