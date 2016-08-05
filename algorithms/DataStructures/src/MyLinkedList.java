/**
 * Created by vishnu on 2/22/16.
 */
public class MyLinkedList {


    Node head = null;
    Node start = null;
    int size = 0;
    class Node {
        int value;
        Node next;
        Node prev;
        public Node(int value) {
            this.value = value;
        }
    }

    public void addItem(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            start = node;
        } else {
            head.next = node;
            node.prev = head;
            head = node;
        }
        size++;
    }



    public int getSize() {
        return size;
    }

    public int get() {
        return head.value;
    }
    public int remove() {
        if (head!= null) {
            int val = head.value;
            head.prev.next = null;
            head = head.prev;
            return val;
        }
        return -1;
    }

    public void addItemAt(int value,int index){
        Node node = start;
        Node newNode = new Node(value);
        int i =0;
        if (node != null) {
            while(i!=index) {
                i++;
                node = node.next;
                if (node == null)
                    return;
            }
            node.prev.next = newNode;
            newNode.prev = node.prev;
            newNode.next = node;
        }
    }

    public int removeItemAt(int index) {
        Node node = start;
        if (node == null)
            return -1;
        int i =0;
        while(i!=index) {
            i++;
            node = node.next;
            if(node == null)
                break;
        }
        if (node == null)
            return -1;
        else {
            int val = node.value;
            node.prev.next = node.next;
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            return val;
        }
    }

    public String toString() {
        Node node = start;
        String result ="";
        while(node!=null) {
            result += node.value+" ";
            node = node.next;
        }
        return result;
    }


    public static void main(String[] args) {
        MyLinkedList ml = new MyLinkedList();
        ml.remove();
        ml.addItem(1);
        System.out.println(ml.toString());
        ml.addItem(3);
        System.out.println(ml.toString());
        ml.addItem(1);
        System.out.println(ml.toString());
        ml.addItem(6);
        System.out.println(ml.toString());
        ml.removeItemAt(5);
        System.out.println(ml.getSize());
        ml.addItemAt(100,2);
        System.out.println(ml.toString());
        System.out.println("top "+ml.get());
        ml.removeItemAt(2);
        System.out.println(ml.toString());

    }

}
