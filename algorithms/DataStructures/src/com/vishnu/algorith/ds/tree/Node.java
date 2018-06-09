package com.vishnu.algorith.ds.tree;

/**
 * Created by vviswanath on 3/24/18.
 */
class Node {

    Node left = null;
    Node right = null;
    int value;

    public Node(int value) {
        this.value = value;
    }

    public void print() {
        System.out.println(value);
        if (this.left != null) this.left.print();
        if (this.right != null) this.right.print();
    }

    @Override
    public boolean equals(Object other) {
        return ((Node) other).value == this.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
