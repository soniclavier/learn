/**
 * Created by vviswanath on 4/22/18.
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int l, int r, int[] temp) {
        if (r - l <= 1) return;
        int mid = (l + r)/2;
        mergeSort(arr, l, mid, temp);
        mergeSort(arr, mid, r, temp);
        merge(arr, l, mid, r, temp);
    }

    public static void merge(int[] arr, int l, int m, int r, int[] temp) {
        for(int i = l; i < r; i++) {
            temp[i] = arr[i];
        }
        int k = l;
        int i = l;
        int j = m;
        for(; i < m && j < r;) {
            if (temp[i] < temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while(i < m) {
            arr[k++] = temp[i++];
        }
    }

    public static void main(String[] args) {
        int[] tosort = new int[]{4,6,3,1,2};
        int[] sorted = new int[tosort.length];

        mergeSort(tosort, 0, tosort.length, sorted);

        for(int i : tosort) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
