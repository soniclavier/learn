package com.vishnuviswanath.algorithm.rec;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vviswanath on 4/2/18.
 *
 * its a combination problem, not a permutation problem.
 */
public class Coins {

    public static int count(int n, int[] denoms, int index, Map<Integer, Integer> mem) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (index == denoms.length) return 0;
        if (mem.containsKey(n)) return mem.get(n);
        int c = 0;
        int denom = denoms[index];
        for(int i = 0; i * denom <= n; i++) {
            c += count(n - (i * denom), denoms, index + 1, mem);
        }
        mem.put(n, c);
        return c;
    }

    public static int count(int n) {
        int[] denoms = new int[]{1, 25, 10, 5};
        return count(n, denoms, 0, new HashMap<>());
    }

    public static void main(String[] args) {
        System.out.println(count(10));
    }
}
