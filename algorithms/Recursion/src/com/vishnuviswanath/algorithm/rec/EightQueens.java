package com.vishnuviswanath.algorithm.rec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vviswanath on 4/13/18.
 */
public class EightQueens {

    public static void eightQueens() {
        eightQueensHelper(new ArrayList<>(), 0, 0);
    }

    private static void eightQueensHelper(List<Point> positions, int x, int y) {
        /*for(Point p : positions) {
            System.out.print("("+p.x +", "+p.y+") ");
        }
        System.out.println();*/

        if (positions.size() == 8) {
            for(Point p : positions) {
                System.out.print("("+p.x +", "+p.y+") ");
            }
            System.out.println();
            return;
        }

        if (x >= 8) return;

        for(int i = x; i < 8; i++) {
            for (int j = y; j < 8; j++) {
                if(canPlace(positions, i, j)) {
                    positions.add(new Point(i, j));
                    eightQueensHelper(positions, i + 1, 0);
                    positions.remove(positions.size() - 1);
                }
            }
        }
    }

    private static boolean canPlace(List<Point> positions, int x, int y) {
        for(Point p: positions) {
            if (p.x == x || p.y == y) return false;
            if (Math.abs(p.x - x) - Math.abs(p.y - y) == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        eightQueens();
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

