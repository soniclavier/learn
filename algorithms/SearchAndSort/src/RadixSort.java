import java.util.ArrayList;
import java.util.List;

/**
 * Created by vviswanath on 4/15/18.
 */
public class RadixSort {

    public static int[] sort(int[] arr) {
        int max = Integer.MIN_VALUE;

        for(int a : arr) {
            max = Math.max(max, a);
        }

        int i = 0;
        int[] a = arr;
        while(Math.pow(10, i) <= max) {
            a = toList(toBuckets(a, i));
            i++;
        }

        return a;
    }

    private static List<List<Integer>> toBuckets(int[] arr, int n) {
        List<List<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            buckets.add(i, new ArrayList<>());
        }
        for(int a: arr) {
            //extract nth digit
            int digit = (int) (a / Math.pow(10, n)) % 10;
            buckets.get(digit).add(a);
        }
        return buckets;
    }

    private static int[] toList(List<List<Integer>> bucket) {
        List<Integer> l = new ArrayList<>();
        for(List<Integer> b : bucket) {
            for(Integer a : b) {
                l.add(a);
            }
        }

        int[] arr = new int[l.size()];
        int i = 0;
        for(int a : l) {
            arr[i++] = a;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = sort(new int[]{1, 5, 99, 2, 34});
        for(int a : arr) {
            System.out.print(a+", ");
        }
    }

}
