package linkedlist;

/**
 * Created by vishnu on 7/18/16.
 */
public class DeleteMiddle {

    public static void main(String[] args) {
        CharNode n1 = new CharNode('a');
        CharNode n2 = new CharNode('b');
        CharNode n3 = new CharNode('c');
        CharNode n4 = new CharNode('d');
        CharNode n5 = new CharNode('e');

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        SinglyLinkedList list = new SinglyLinkedList(n1);
        System.out.println(list);

        SinglyLinkedList list2 = list.deleteMiddle();
        System.out.println(list2);

        SinglyLinkedList list3 = new SinglyLinkedList(new CharNode('z'));
        System.out.println(list3.deleteMiddle());
    }
}


class SinglyLinkedList {
    CharNode head;

    public SinglyLinkedList(CharNode head) {
        this.head = head;
    }

    @Override
    public String toString() {
        String str = "";
        CharNode temp = head;
        str += temp.value;
        while (temp.next != null) {
            temp = temp.next;
            str += temp.value;
        }
        return str;
    }

    SinglyLinkedList deleteMiddle() {
        CharNode p1 = head;
        CharNode p2 = head;
        CharNode prev = null;
        while(p1.hasNext() && p1.next.hasNext()) {
            p1 = p1.next.next;
            prev = p2;
            p2 = p2.next;
        }
        if (prev != null)
            prev.next = p2.next;
        return this;
    }
}

class CharNode implements SinglyNode<Character> {
    CharNode next;
    char value;

    public CharNode(char value) {
        this.value = value;
    }

    /*
    public linkedlist.CharNode clone() {
        linkedlist.CharNode newNode = new linkedlist.CharNode(this.value);
        if (hasNext()) {
            newNode.next = this.next.clone();
        }
        return newNode;
    }*/

    public boolean hasNext() {
        return next != null;
    }

    @Override
    public boolean equals(Object other) {
        return this.value == ((CharNode)other).value;
    }
}

interface SinglyNode<T> {

}
