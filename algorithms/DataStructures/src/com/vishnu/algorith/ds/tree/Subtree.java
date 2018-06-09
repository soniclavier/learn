package com.vishnu.algorith.ds.tree;

/**
 * Created by vviswanath on 3/27/18.
 */
public class Subtree {

    public static boolean isSubtree(Node t1, Node t2) {
        Node t = search(t1, t2);
        return matches(t, t2);
    }

    public static boolean matches(Node t1, Node t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (!t1.equals(t2)) return false;

        boolean matches = true;
        if (t2.left != null) {
            matches = matches(t1.left, t2.left);
        }
        if (matches && t2.right != null) {
            return matches(t1.right, t2.right);
        }
        return matches;
    }

    public static Node search(Node t1, Node t2) {
        if (t1 == null) return null;
        if (t1.equals(t2)) return t1;

        Node t = search(t1.left, t2);
        if (t == null) {
            t = search(t1.right, t2);
        }
        return t;
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        g.left = h;


        Node i = new Node(3);
        Node j = new Node(7);
        Node k = new Node(8);

        i.right = j;
        j.left = k;

        System.out.println(isSubtree(a, i));

    }
}
