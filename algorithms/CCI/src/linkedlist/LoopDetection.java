package linkedlist;


/**
 * Created by vishnu on 7/20/16.
 */
public class LoopDetection {

    public static void main(String[] args) {
        CharNode a = new CharNode('a');
        CharNode b = new CharNode('b');
        CharNode c = new CharNode('c');
        CharNode d = new CharNode('d');
        CharNode e = new CharNode('e');
        CharNode f = new CharNode('f');
        CharNode g = new CharNode('g');
        CharNode h = new CharNode('h');
        CharNode i = new CharNode('i');



        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = d;

        CharNode loopStart = detectLoop(a);
        if (loopStart == null) System.out.println("no loop detected");
        System.out.println(loopStart.value);
    }

    public static CharNode detectLoop(CharNode head) {
        CharNode slowRunner = head.next;
        CharNode fastRunner = head.next.next;
        System.out.println("heere");

        while(!slowRunner.equals(fastRunner)) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
            System.out.println(slowRunner.value+" , "+fastRunner.value);
        }

        System.out.println("Collided  at "+slowRunner.value);

        if (slowRunner == null || fastRunner == null)
            return null;

        fastRunner = head;
        while(!slowRunner.equals(fastRunner)) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next;
        }

        return slowRunner;
    }
}
