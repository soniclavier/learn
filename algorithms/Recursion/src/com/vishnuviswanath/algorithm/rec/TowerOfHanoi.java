package com.vishnuviswanath.algorithm.rec;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by vviswanath on 3/31/18.
 */
public class TowerOfHanoi {


    public static void toh(Deque<Integer> from, Deque<Integer> to, Deque<Integer> temp) {
        move(from.size(), from, to, temp);
    }

    private static void move(int n, Deque<Integer> from, Deque<Integer> to, Deque<Integer> temp) {
        if (n == 1) {
            to.push(from.pop());
        } else {
            move(n - 1, from, temp, to);
            to.push(from.pop());
            move(n - 1, temp, to, from);
        }
    }

    public static void main(String[] args) {
        Deque<Integer> frm = new ArrayDeque<Integer>();
        frm.push(5);
        frm.push(4);
        frm.push(3);
        frm.push(2);
        frm.push(1);

        Deque<Integer> to = new ArrayDeque<Integer>();
        Deque<Integer> temp = new ArrayDeque<Integer>();

        toh(frm, to, temp);
        System.out.println("done");
    }
}
