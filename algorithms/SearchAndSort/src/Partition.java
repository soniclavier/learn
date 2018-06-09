import java.util.Arrays;

public class Partition {

    /**
     *
     * @param arr the array
     * @param k pivot index
     * @return
     */
    private static int partition(int[] arr,int k) {
        int pivot = arr[k];
        int l = 0;
        int r = arr.length - 1;

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
        return l;
    }

    private static int partition2(int[] arr, int k) {
        int pivot = arr[k];
        int i = -1;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
                System.out.println(Arrays.toString(arr));
            }
        }
        swap(arr, i+1, k);
        System.out.println(Arrays.toString(arr));
        return i+1;
    }

    public static void swap(int[] arr, int l, int r) {
        int t = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 3, 4, 2, 5, 7, 10, 11};
        for(int a : arr) {
            System.out.print(a+" ");
        }
        System.out.println();
        System.out.println("------------");

        int p =partition2(arr, 5);
        for(int a : arr) {
            System.out.print(a+" ");
        }
        System.out.println();
        System.out.println(p);
    }
}
