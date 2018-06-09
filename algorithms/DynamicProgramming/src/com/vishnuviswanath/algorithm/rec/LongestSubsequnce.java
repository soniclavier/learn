package com.vishnuviswanath.algorithm.rec;

/**
 * Created by vviswanath on 3/30/18.
 */
public class LongestSubsequnce {

    /*
    LONGEST NON DECREASING SUBSEQUENCE

    A DP problem is made up of smaller problem.
    1. Recursive formula
    2. buttom up step


     */
    public static int[] longestSubsequnce(int[] arr) {
        Tuple<Integer, Integer>[] L = new Tuple[arr.length];

        for(int i = 0; i < L.length; i++) {
            int max = 0;
            int maxK = i;
            for (int k = 0; k < i; k++) {
                if (arr[i] > arr[k]) {
                    if (L[k].t1 > max) {
                        max = L[k].t1;
                        maxK = k;
                    }
                }
            }
            L[i] = new Tuple(1 + max, maxK);
        }

        int maxIndex = 0;
        for(int i = 1; i < L.length; i++) {
            if (L[i].t1 > L[maxIndex].t1) {
                maxIndex = i;
            }
        }
        int[] seq = new int[L[maxIndex].t1];
        for(int i = seq.length - 1, j = maxIndex; i >= 0; i--) {
            seq[i] = arr[j];
            j = L[j].t2;
        }

        return seq;
    }
    public static void main(String[] args) {

        int[] arr = new int[]{2, 7, 5, 6, 1, 8, 4, 3, 9, 0};
        int[] lis = longestSubsequnce(arr);

        for(int i : lis) {
            System.out.print(i+" ");
        }
        System.out.println();

    }
}
