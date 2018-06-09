package com.vishnuviswanath.algorithm.rec;

/**
 * Created by vviswanath on 4/22/18.
 */
public class Permutation2 {

    public static<T> void permute(T[] arr, int l, int r) {
        if (l == r) {
            for(T a: arr) {
                System.out.print(a+" ");
            }
            System.out.println();
        }
        else {
            for (int i = l; i < r; i++) {
                swap(arr, l, i);
                permute(arr, l + 1, r);
                swap(arr, l, i);
            }
        }
    }

    public static<T> void swap(T[] arr, int f, int t) {
        T temp = arr[f];
        arr[f] = arr[t];
        arr[t] = temp;
    }

    public static void main(String[] args) {
        String str = "abc";
        Character[] chars = new Character[str.length()];
        int i = 0;
        for(char ch : str.toCharArray()) {
            chars[i++] = ch;
        }
        Permutation2.<Character>permute(chars, 0, 3);
    }
}
