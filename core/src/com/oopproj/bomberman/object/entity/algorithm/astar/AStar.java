package com.oopproj.bomberman.object.entity.algorithm.astar;

import java.util.PriorityQueue;
import java.util.Stack;

public class AStar {
    private Stack<Square> stackPos = new Stack<>();

    public AStar(Square initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Dau vao bi null");
        }

        PriorityQueue<Node> findNodesPQ = new PriorityQueue<>();
        Node init = new Node(initial, null);
        findNodesPQ.add(init);
        while (true) {
            Node node = findNodesPQ.peek();
            if (node.square.isGoal()) {
                break;
            }
            Node getNode = findNodesPQ.poll();
            Stack<Square> neighbors = getNode.square.neighbors();
            for (Square square : neighbors) {
                if (getNode.pre == null) {
                    findNodesPQ.add(new Node(square, getNode));
                } else {
                    if (!square.equals(getNode.pre.square)) {
                        findNodesPQ.add(new Node(square, getNode));
                    }
                }
            }
        }
        Node node = findNodesPQ.peek();
        while (node.pre != null) {
            stackPos.push(node.square);
            node = node.pre;
        }
    }

    public Stack<Square> getStack() {
        return stackPos;
    }

    private static class Node implements Comparable<Node> {
        private final Square square;
        private final int priority;
        private final Node pre;
        private int step;
        private int manhattan;

        public Node(Square square, Node pre) {
            this.square = square;
            this.pre = pre;
            if (pre == null) {
                step = 0;
            } else {
                step = pre.step + 1;
            }
            this.manhattan = square.manhattan();
            priority = step + manhattan;
        }

        @Override
        public int compareTo(Node o) {
//            return Integer.compare(this.priority, o.priority);
            if (this.priority > o.priority) return -1;
            if (this.priority < o.priority) return 1;
            return 0;
        }
    }


}
