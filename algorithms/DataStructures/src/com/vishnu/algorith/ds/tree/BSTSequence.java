package com.vishnu.algorith.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vviswanath on 3/26/18.
 */
public class BSTSequence {

    public static List<ArrayList<Node>> bstSequences(Node root) {
        if (root == null) return new ArrayList<>();
        ArrayList<Node> prefix = new ArrayList<>();
        prefix.add(root);

        List<ArrayList<Node>> lSeq = bstSequences(root.left);
        List<ArrayList<Node>> rSeq = bstSequences(root.right);

        ArrayList<ArrayList<Node>> results = new ArrayList<>();
        for (ArrayList<Node> ls : lSeq) {
            for (ArrayList<Node> rs: rSeq) {
                weave(ls, rs, prefix, results);
            }
        }

        return results;
    }


    private static void weave(ArrayList<Node> lSeq, ArrayList<Node> rSeq, ArrayList<Node> prefix, ArrayList<ArrayList<Node>> results) {
        if (lSeq.size() == 0 || rSeq.size() == 0) {
            ArrayList<Node> result = (ArrayList<Node>) prefix.clone();
            result.addAll(lSeq);
            result.addAll(rSeq);
            results.add(result);
        }

        prefix.add(lSeq.remove(0));
        weave(lSeq, rSeq, prefix, results);

        lSeq.add(prefix.remove(prefix.size() - 1));

        prefix.add(rSeq.remove(0));
        weave(lSeq, rSeq, prefix, results);

        rSeq.add(prefix.remove(prefix.size() - 1));
    }

    private static void print(List<Node> nodes) {
        for(Node node : nodes) {
            System.out.print(node.value+" ");
        }
        System.out.println();
    }
}
