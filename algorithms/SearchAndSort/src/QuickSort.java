import java.util.Arrays;

/**
 * Created by vviswanath on 4/15/18.
 */
public class QuickSort {

    private static void quickSort(int[] arr, int l, int r) {
        int p = partition(arr, l, r);
        if (l < p - 1) {
            quickSort(arr, l, p - 1);
        }
        if (p < r) {
            quickSort(arr, p, r);
        }
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[(l + r) / 2];

        while (l <= r) {
            while(arr[l] < pivot) l++;

            while(arr[r] > pivot) r--;

            if (l <= r) {
                int t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
                l++;
                r--;
            }
        }
        System.out.println(l+" , "+arr[l]);
        System.out.println(Arrays.toString(arr));
        return l;
    }


    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length  - 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 2, 3, 4, 5, 7, 10, 11};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
