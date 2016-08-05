/**
 * Created by vishnu on 2/22/16.
 */
public class MyQueue {

    int[] items;
    int head = 0;
    public MyQueue(int size) {
        items = new int[size];
    }

    public void push(int item) {
        if (head == items.length)
            return;
        items[head]=item;
        head++;
        for(int i=0;i<head;i++) {
            System.out.print(items[i]+",");
        }
        System.out.println();
    }

    public int pop() {
        if (head >0)
            return items[--head];
        return -1;
    }

    public int peek() {
        return items[head];
    }

    public int size() {
        return head;
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue(5);
        q.push(10);
        q.push(1);
        q.push(40);
        q.push(3);
        q.push(6);
        q.push(9);
        while(q.size()!=0){
            System.out.println();
            System.out.print(q.size()+" ");
            System.out.print(q.pop());
        }

    }
}
