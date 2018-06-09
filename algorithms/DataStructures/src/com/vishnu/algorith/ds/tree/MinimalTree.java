package com.vishnu.algorith.ds.tree;

/**
 * Created by vviswanath on 3/24/18.
 */
public class MinimalTree {

    public static Node constructBtree(int[] a, int l, int r) throws Exception {
        if (a == null || a.length == 0) throw new Exception("Array is empty");
        if (l >= r) return null;

        int m = l + (r - l) / 2;

        Node root = new Node(a[m]);
        root.right = constructBtree(a, m + 1, r);
        root.left = constructBtree(a, l, m);
        return root;
    }

    public static void main(String[] args) throws Exception {
        int[] a = {1, 4,7,8, 10, 20};
        Node root = constructBtree(a, 0, a.length);
        root.print();

    }

}
