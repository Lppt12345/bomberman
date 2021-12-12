package com.oopproj.bomberman.object.entity.algorithm.astar;

import com.oopproj.bomberman.object.ground.Grass;
import com.oopproj.bomberman.utils.Map;

import java.util.Stack;

public class Square {
    private Map map;
    private int position;           // o bat dau
    private int destination;   // o ket thuc
    private int col;

    public Square(Map map, int position, int des) {
        this.map = map;
        col = map.getColumn();
        this.destination = des;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public boolean equals(Object y) {
        if (y instanceof Square) {
            Square other = (Square) y;
            return other.position == position && other.map == map && other.destination == destination;
        } else {
            return false;
        }
    }

    public boolean isGoal() {
        return position == destination;
    }

    /**
     * Tinh khoang cach manhattan
     *
     * @return kc manhattan tinh theo int
     */
    public int manhattan() {
        int colC = (int) Math.abs(map.getMap().get(position).getPos().x - map.getMap().get(destination).getPos().x);
        int rowC = (int) Math.abs(map.getMap().get(position).getPos().y - map.getMap().get(destination).getPos().y);
        return colC + rowC;
    }

    public Stack<Square> neighbors() {
        Stack<Square> neighB = new Stack<>();
        if (position - col > 0 && (map.getMap().get(position - col) instanceof Grass)) { // check o tren
            neighB.push(new Square(map, position - col, destination));
        }
        if (position + col < map.getMap().size() && (map.getMap().get(position + col) instanceof Grass)) { // check o duoi
            neighB.push(new Square(map, position + col, destination));
        }
        if (position - 1 > 0 && (map.getMap().get(position - 1) instanceof Grass)) { // check ben trai
            neighB.push(new Square(map, position - 1, destination));
        }
        if (position + 1 > 0 && (map.getMap().get(position + 1) instanceof Grass)) { // check ben phai
            neighB.push(new Square(map, position + 1, destination));
        }
        return neighB;
    }


}
