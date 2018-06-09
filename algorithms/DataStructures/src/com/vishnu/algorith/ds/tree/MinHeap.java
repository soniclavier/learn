package com.vishnu.algorith.ds.tree;

/**
 * Created by vviswanath on 4/15/18.
 */
public class MinHeap {

    int[] arr;
    int last;
    int size;

    public MinHeap(int size) {
        this.size = size + 1;
        this.arr = new int[this.size];
        this.last = 1;
    }

    public int extractMin() {
        int min = arr[1];
        arr[1] = arr[last - 1];
        arr[last - 1] = 0;
        last--;
        bubbleDown(1);
        return min;
    }

    public void insert(int n) {
        if (last == size ) return; //heap is full
        arr[last++] = n;
        bubbleUp(last-1);
    }

    private void bubbleUp(int i) {
        if (i <= 0) return; //exit condition
        if (i >= last) return;

        int p = parent(i);
        if (p <= 0) return; //no parent
        if(arr[p] > arr[i]) {
            int t = arr[i];
            arr[i] = arr[p];
            arr[p] = t;
            bubbleUp(p);
        }
    }


    private void bubbleDown(int i) {
        //check if i is within bounds;
        if (i >= last) return;

        int leftIndex = left(i);
        int rightIndex = right(i);

        int swapIndex = -1;
        //figure out swap index;
        int lValue = Integer.MAX_VALUE;
        int rValue = Integer.MAX_VALUE;
        if (leftIndex < last) {
            lValue = arr[leftIndex];
        }
        if (rightIndex < last) {
            rValue = arr[rightIndex];
        }

        if (lValue < rValue && arr[i] > lValue) {
            swapIndex = leftIndex;
        } else if (rValue < lValue && arr[i] > rValue) {
            swapIndex = rightIndex;
        }


        if (swapIndex != -1) {
            int t = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = t;
            bubbleDown(swapIndex);
        }
    }

    private int left(int i) {
        return i*2;
    }
    private int right(int i) {
        return i*2 + 1;
    }
    private int parent(int i) {
        return i/2;
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);
        heap.insert(10);
        heap.insert(14);
        heap.insert(5);
        heap.insert(12);
        heap.insert(1);
        heap.insert(19);
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        heap.insert(9);
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());

    }


}
