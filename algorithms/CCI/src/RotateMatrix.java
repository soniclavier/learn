/**
 * Created by vishnu on 7/17/16.
 */
public class RotateMatrix {


    public static int[][] rotate(int[][] input) {
        int n = input.length;
        for(int i=0;i<n;i++) {
            for(int j=i;j<n-i-1;j++) {
                int temp = input[i][j];

                int k,l,p,q,r,s;
                k = j;
                l = (n-1)-i;

                p = l;
                q = (n-1)-k;

                r = q;
                s = (n-1)-p;

                input[i][j] = input[r][s];
                input[r][s] = input[p][q];
                input[p][q] = input[k][l];
                input[k][l] = temp;

                System.out.println("i = "+i+", j = "+j+",k = "+k+", l = "+l+", p = "+p+", q = "+q+", r = "+r+",s = "+s);
            }
        }
        return input;
    }

    public static void main(String[] args) {
        int[][] a = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        int[][] rotated = rotate(a);

        for(int i=0;i<rotated.length;i++) {
            for(int j=0;j<rotated.length;j++) {
                System.out.print(rotated[i][j]+" ");
            }
            System.out.println();
        }
    }
}
