package cci;

/**
 * Created by vviswanath on 3/18/18.
 */
public class PartitionList {

    public static void main(String[] args) {
        Node head = new Node(5);
        head.withTail(2).withTail(1).withTail(3).withTail(4).withTail(2).withTail(1);
        head.print();

        partition(head, 5).print();


    }

    public static  Node partition(Node node, int part) {
        Node head = node;
        Node tail = node;

        while(node != null) {
            Node next = node.next;
            if (node.val < part) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }
            node = next;

        }
        tail.next = null;
        return head;
    }
}

class Node {
    Node next;
    int val;

    public Node(int val) {
        this.val = val;
    }


    public Node withTail(int val) {
        Node next = new Node(val);
        this.next = next;
        return next;
    }

    public void print() {
        System.out.print(val);
        if (next != null) {
            System.out.print("->");
            next.print();
        } else {
            System.out.println();
        }
    }


}
