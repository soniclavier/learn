import java.util.Arrays;

public class SelectionRank {

    /**
     * Kth smallest element in an array
     */

    public static int kthSmallest(int[] arr, int k) {
        return kthSmallestHelper(arr, k, 0, arr.length);
    }

    /**
     *
     * @param arr
     * @param k from 1 to arr.length
     * @param l
     * @param r
     * @return
     */
    private static int kthSmallestHelper(int[] arr, int k, int l, int r) {
        int p = (l + r )/2;
        int pl = partition(arr, p, l, r);
        int leftSize = pl - l;
        if (leftSize == 0) return arr[pl];
        if (leftSize  == k) {
            return max(arr, l, l + leftSize);
        }
        if (k < leftSize) {
            return kthSmallestHelper(arr, k, l, pl);
        } else {
            return kthSmallestHelper(arr, k - leftSize , pl, r);
        }
    }

    private static int partition(int[] arr, int k, int l, int r) {
        //move k to the right
        swap(arr, k, r - 1);
        int pivot = arr[r - 1];
        int i  = l - 1;
        for(int j = l; j < r  - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r - 1);
        return i + 1;
    }

    private static int max(int[] arr, int l, int r) {
        int max = Integer.MIN_VALUE;
        for(int i = l; i < r; i++) {
            max = Math.max(arr[i], max);
        }

        return max;
    }

    private static void swap(int[] arr, int l, int r) {
        int t = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
        //swap a, b
        //a = a + b
        //b = a - b
        //a = a - b
    }


    public static void main(String[] args) {
        //int[] arr = {2, 7, 10, 3, 5, 4, 0, 6};
        //System.out.print(kthSmallest(arr, 2)+" ");

        for(int i = 1; i <= 8; i++) {
            int[] arr = {2, 7, 10, 3, 5, 4, 0, 6};
            System.out.print(kthSmallest(arr, i)+" ");
        }
        System.out.println();

        //partition(arr, arr.length - 1, 0 , arr.length);
        //System.out.println(Arrays.toString(arr));
    }


}
