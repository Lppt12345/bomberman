package com.oopproj.bomberman.object.entity.algorithm.astar;

import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.utils.Map;

import java.util.Stack;

public class Square {
    private Map map;
    private int pos;           // o bat dau
    private int destination;   // o ket thuc
    private int col;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Square(Map map, int pos, int des) {
        this.map = map;
        col = map.getColumn();
        this.destination = des;
        this.pos = pos;
    }

    public boolean equals(Object y) {
        if (y instanceof Square) {
            Square other = (Square) y;
            return other.pos == pos && other.map == map && other.destination == destination;
        } else {
            return false;
        }
    }

    public boolean isGoal() {
        return pos == destination;
    }

    /**
     * Tinh khoang cach manhattan
     *
     * @return kc manhattan tinh theo int
     */
    public int manhattan() {
        int colC = (int) Math.abs(map.getMap().get(pos).getPos().x - map.getMap().get(destination).getPos().x);
        int rowC = (int) Math.abs(map.getMap().get(pos).getPos().y - map.getMap().get(destination).getPos().y);
        return colC + rowC;
    }

    public Iterable<Square> neighbors() {
        Stack<Square> neighB = new Stack<>();
        if (pos - col > 0 && (map.getMap().get(pos - col) instanceof Grass)) { // check o tren
            neighB.push(new Square(map, pos - col, destination));
        }
        if (pos + col < map.getMap().size() && (map.getMap().get(pos + col) instanceof Grass)) { // check o duoi
            neighB.push(new Square(map, pos + col, destination));
        }
        if (pos - 1 > 0 && (map.getMap().get(pos - 1) instanceof Grass)) { // check ben trai
            neighB.push(new Square(map, pos - 1, destination));
        }
        if (pos + 1 > 0 && (map.getMap().get(pos + 1) instanceof Grass)) { // check ben phai
            neighB.push(new Square(map, pos + 1, destination));
        }
        return neighB;
    }


}
