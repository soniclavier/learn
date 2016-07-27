package linkedlist;

/**
 * Created by vishnu on 7/19/16.
 */
public class SumList {

    public static void main(String[] args) {

        IntNode six = new IntNode(6);
        IntNode one = new IntNode(1);
        IntNode seven = new IntNode(7);

        six.next = one;
        one.next = seven;

        IntNode five = new IntNode(5);
        IntNode one2 = new IntNode(1);
        IntNode six2 = new IntNode(6);
        IntNode four = new IntNode(5);

        five.next = one2;
        one2.next = six2;
        six2.next = four;

        System.out.println(six);
        System.out.println(five);

        IntNode sum = sum(six,five);
        System.out.println(sum);


    }

    public static IntNode sum(IntNode head1, IntNode head2) {
        int l1 = length(head1);
        int l2 = length(head2);


        if (l1 > l2) {
            head2 = padZeroNode(head2, (l1-l2));
        } else if (l2 > l1) {
            head1 = padZeroNode(head1, (l2-l1));
        }

        System.out.println("padded "+head1);
        System.out.println("padded "+head2);

        IntNodeWrapper sumWrapper = sumHelper(head1,head2);
        if (sumWrapper.carry >0) {
            IntNode carryNode = new IntNode(sumWrapper.carry);
            carryNode.next = sumWrapper.node;
            return carryNode;
        } else
            return sumWrapper.node;
    }


    public static IntNodeWrapper sumHelper(IntNode head1, IntNode head2) {
        if (head1 == null && head2 == null)
            return new IntNodeWrapper(null,0);

        IntNodeWrapper innerSum  = sumHelper(head1.next, head2.next);

        int sum = head1.value + head2.value + innerSum.carry;
        IntNode sumNode = new IntNode(sum%10);
        int carry = sum/10;
        sumNode.next = innerSum.node;

        return new IntNodeWrapper(sumNode, carry);
    }

    public static int length(IntNode n) {
        if (n == null) return 0;
        else return 1+length(n.next);
    }

    public static IntNode padZeroNode(IntNode n, int count) {
        if (count == 0) return n;
        IntNode prev = null;
        while(count > 0) {
            IntNode zero = new IntNode(0);
            if (prev != null) zero.next = prev;
            else zero.next = n;

            prev = zero;
            count--;
        }
        return prev;
    }
}



class IntNode implements SinglyNode {
    IntNode next;
    int value;

    public IntNode(int value) {
        this.value = value;
    }

    public String toString() {
        IntNode temp = this;
        String str = "";
        while(temp != null) {
            str += temp.value;
            temp = temp.next;
        }
        return str;
    }
}

class IntNodeWrapper {
    IntNode node;
    int carry = 0;

    public IntNodeWrapper(IntNode node, int carry) {
        this.node = node;
        this.carry = carry;
    }
}




