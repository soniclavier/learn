package cci;

/**
 * Created by vviswanath on 3/18/18.
 */
public class AddNumbers {

    public static void main(String[] args) {
        Node head1 = new Node(2);
        head1.withTail(3).withTail(5);

        Node head2 = new Node(9);
        head2.withTail(2).withTail(9);

        Node sum = add(head1, head2);

        sum.print();

    }

    public static Node add(Node head1, Node head2) {
        Node sumHead = null;
        Node prev = null;
        int c = 0;
        while(head1 != null  || head2 != null) {
            int s = c;
            if (head1 != null) {
                s += head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                s += head2.val;
                head2 = head2.next;
            }

            c = s / 10;
            s = s % 10;

            Node curr = new Node(s);
            if (sumHead == null) {
                sumHead = curr;
            }

            if (prev != null) {
                prev.next = curr;
            }

            prev =  curr;
        }

        if (c > 0) {
            Node carry = new Node(c);
            carry.next = sumHead;
            sumHead = carry;
        }
        return sumHead;
    }
}
