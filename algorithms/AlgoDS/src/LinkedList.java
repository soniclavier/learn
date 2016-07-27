/**
 * Created by vishnu on 6/9/16.
 */
public class LinkedList {

    Node head = null;
    Node tail = null;

    public void add(Node node) {
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        tail = node;
    }

    public void delete(Node node) {
        Node prev = null;
        Node curr = head;
        while(curr != null) {
            if(curr.equals(node)) {
                if (prev == null) {
                    //head case
                    head = curr.next;
                    return;
                }
                prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;

        }
    }

    public Node search(Node node) {
        Node prev = null;
        Node curr = head;
        while(curr != null) {
            if(curr.equals(node)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public void display() {
        Node curr = head;
        while(curr != null) {
            System.out.print(curr.val+" -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args){
        LinkedList ls = new LinkedList();
        Node n1 = new Node();
        n1.val = "n1";
        Node n2 = new Node();
        n2.val = "n2";
        Node n3 = new Node();
        n3.val = "n3";
        ls.add(n1);
        ls.add(n2);
        ls.add(n3);
        ls.display();
        ls.delete(n2);
        ls.display();
        System.out.println(ls.search(n1));
        System.out.println(ls.search(n2));
    }

}

class Node {
    String val;
    Node next;

    @Override
    public boolean equals(Object other) {
        return this.val.equals(((Node)other).val);
    }

    @Override
    public String toString() {
        return val;
    }
}
