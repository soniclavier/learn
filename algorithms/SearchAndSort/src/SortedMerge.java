import java.util.Arrays;

/**
 * Created by vviswanath on 4/15/18.
 * A IS SORTED, B IS NOT, A HAS SPACE AT THE END.
 */
public class SortedMerge {

    public static void sortedMerge(int[] A, int[] B, int lastIndex) {
        int i = lastIndex;
        int j = B.length - 1;

        Arrays.sort(B);

        int l = A.length - 1;

        while(i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[l--] = A[i--];
            } else {
                A[l--] = B[j--];
            }
        }

        while(j >= 0) {
            A[l--] = B[j--];
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{0, 8, 16, 20, 26, 32, 40, 0, 0, 0, 0};
        sortedMerge(A, new int[]{9, 15, 22, 35}, 6);
        System.out.println("done");
    }
}
