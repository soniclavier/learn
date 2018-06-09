package com.vishnu.algorith.ds.tree;

import java.util.*;

/**
 * Created by vviswanath on 3/24/18.
 */
public class ListOfDepths {

    public static Collection<List<Node>> listOfDepths(Node root) {
        Map<Integer, List<Node>> levelNodes = new HashMap<>();

        recursiveInsert(root, 0, levelNodes);
        return levelNodes.values();
    }

    private static void recursiveInsert(Node root, int level, Map<Integer, List<Node>> levelNodes) {
        if (root == null) return;
        if (levelNodes.containsKey(level)) {
            List<Node> nodes = levelNodes.get(level);
            nodes.add(root);
            levelNodes.put(level, nodes);
        } else {
            List<Node> nodes = new ArrayList<>();
            nodes.add(root);
            levelNodes.put(level, nodes);
        }
        recursiveInsert(root.left, level +1, levelNodes);
        recursiveInsert(root.right, level +1, levelNodes);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(4);
        Node d = new Node(5);
        Node e = new Node(6);

        root.left = a;
        root.right = b;
        a.left = c;
        a.right = d;
        b.left = e;

        Collection<List<Node>> lofDepths = listOfDepths(root);
    }
}
