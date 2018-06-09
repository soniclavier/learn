package com.vishnuviswanath.algorithm.rec;

import java.util.*;

/**
 * Created by vviswanath on 4/22/18.
 *
 */
public class Combination {
    /**
     * Without replacement
     * @param arr original array
     * @param aIndex index of arr array
     * @param data array containing the combination
     * @param dIndex index of data array
     * @param r = number of items in the combination
     * @param <T> type T of arrays arr & data
     */
    private static<T> void combination(T[] arr, int aIndex, T[] data, int dIndex, int r) {
        if (dIndex == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(data[i]);
            }
            System.out.println();
            return;
        }
        if(aIndex >= arr.length)
            return;
        data[dIndex] = arr[aIndex];
        combination(arr, aIndex+1, data, dIndex+1, r);
        combination(arr, aIndex + 1, data, dIndex, r);
    }

    /*
    With replacement. Source is inexhaustible
     */
    private static<T> void combinationWithReplc(T[] arr, int aIndex, T[] data, int dIndex, int r) {
        if (dIndex == r) {
            for (int i = 0; i < dIndex; i++) {
                System.out.print(data[i]);
            }
            System.out.println();
            return;
        }
        for(int i = aIndex; i < arr.length; i++) {
            data[dIndex] = arr[i];
            combinationWithReplc(arr, i, data,  dIndex + 1, r);
        }
    }

    /*
    Without replacement but source has duplicates
    - convert source to <item, count> tuples
    - apply same logic as combination with replacement.
     */
    private static<T> void combinationWithDups(T[] arr, int aIndex, T[] data, int dIndex, int r) {
        Map<T, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int c = map.get(arr[i]);
                map.put(arr[i], c + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        List<TupleC<T>> arrList = new ArrayList<>();
        for(T key : map.keySet()) {
            arrList.add(new TupleC<T>(key, map.get(key)));
        }
        combinationWithDupsHelper(arrList, aIndex, data, dIndex, r);

    }

    private static<T> void combinationWithDupsHelper(List<TupleC<T>> list, int aIndex, T[] data, int dIndex, int r) {
        if (dIndex == r) {
            for (int i = 0; i < dIndex; i++) {
                System.out.print(data[i]);
            }
            System.out.println();
            return;
        }
        for(int i = aIndex; i < list.size(); i++) {
            TupleC<T> t = list.get(i);
            if (t.count == 0) continue;
            data[dIndex] = t.value;
            t.count--;
            combinationWithDupsHelper(list, i , data,  dIndex + 1, r);
            t.count++;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[5];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;


        Integer[] arr2 = new Integer[4];
        arr2[0] = 1;
        arr2[1] = 2;
        arr2[2] = 3;
        arr2[3] = 4;
        int r = 4;
        Integer[] data = new Integer[4];
        //Combination.<Integer>combination(arr, 0, data, 0, r);

        System.out.println("_------_");
        combinationWithReplc(arr2, 0, data, 0, r);

        System.out.println("_------_");
        combinationWithDups(arr, 0, data, 0, r);
    }
}

class TupleC<T> {
    T value;
    int count;

    public TupleC(T value, int count) {
        this.value = value;
        this.count = count;
    }

    public boolean equals(Object other) {
        TupleC otherTup = (TupleC) other;
        return this.value == otherTup.value;
    }
}
