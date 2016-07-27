/**
 * Created by vishnu on 6/8/16.
 */
public class StackMayBe {

    Integer[] elements;
    int head;
    int size;


    public StackMayBe(int size) {
        this.elements = new Integer[size];
        this.size = size;
    }

    public void insert(Integer item) {
        elements[head] = item;
        head++;
    }

    public Integer get(int n) {
        return elements[n];
    }

    public void delete(Integer item) {
        int i =0;
        for(;i<=head;i++) {
            if (elements[i].equals(item))
                break;
        }
        if(i<=head) {
            for(;i<head;i++)
                elements[i] = elements[i+1];
            head--;
        }
    }

    public void insert(int n,Integer item) {
        if (head == size || n > head+1)
            return;
        int i = head;
        for(;i>=n;i--)
            elements[i+1]=elements[i];
        elements[i+1] = item;
        head++;
    }

    public void print() {
        for(int i=0;i<head;i++) {
            System.out.print(elements[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StackMayBe mylist = new StackMayBe(10);
        mylist.insert(1);
        mylist.insert(2);
        mylist.insert(3);
        mylist.print();
        mylist.delete(2);
        mylist.insert(2,5);
        mylist.print();
    }
}
