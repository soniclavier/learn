package com.vishnu.algorith.ds.tree;

public class BiNodeToDLL {
    public static BiNode biNodeToLL(BiNode root) {
        biNodeToLL(root, null);
        return getHead(root);
    }

    private static BiNode biNodeToLL(BiNode root, BiNode prev) {
        if (root == null) return null;
        BiNode lPrev = biNodeToLL(root.left, prev);
        if (lPrev != null) prev = lPrev;
        if (prev != null) {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        BiNode rPrev = biNodeToLL(root.right, prev);
        if (rPrev != null) {
            prev = rPrev;
        }
        return prev;
    }

    private static BiNode getHead(BiNode root) {
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }


    public static void main(String[] args) {
        BiNode n1 = new BiNode(30);
        BiNode n2 = new BiNode(11);
        BiNode n3 = new BiNode(5);
        BiNode n4 = new BiNode(17);
        BiNode n5 = new BiNode(15);
        BiNode n6 = new BiNode(50);
        BiNode n7 = new BiNode(44);
        BiNode n8 = new BiNode(55);

        n1.left = n2;  //30 -> 11
        n2.left = n3; //11 -> 5
        n2.right = n4; //11 -> 17
        n4.left = n5; //17 -> 15

        n1.right = n6; // 30 -> 50
        n6.left = n7; //50 ->  44
        n6.right = n8; //50 -> 55

        BiNode head = biNodeToLL(n1);
        while(head != null) {
            System.out.print(head.data+" ");
            head = head.right;
        }
    }

}

class BiNode {
    BiNode left, right;
    int data;

    public BiNode(int data) {
        this.data = data;
    }
}
