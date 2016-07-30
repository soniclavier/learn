/**
 * Created by vishnu on 2/21/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Mixtures {

    public static int minimumSmoke(int[] colors) {
        if (colors.length == 0)
            return 0;
        int[][] mem = new int[colors.length][colors.length];
        int[][] newColor = new int[colors.length][colors.length];

        for (int d = 0; d < colors.length; d++) {
            int i = 0;
            int j = d;
            while (i < colors.length && j < colors.length) {
                if (i == j) {
                    //mem[i][j] = colors[i];
                    newColor[i][j] = colors[i];
                } else {
                    int min = Integer.MAX_VALUE;
                    int minColor = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int temp = mem[i][k]+mem[k+1][j]+newColor[i][k]*newColor[k+1][j];
                        int tempColor = (newColor[i][k] + newColor[k + 1][j]) % 100;
                        if (temp < min) {
                            min = temp;
                            minColor = tempColor;
                        }
                    }
                    mem[i][j] = min;
                    newColor[i][j] = minColor;
                }
                i++;
                j++;
            }
            //System.out.println();
        }

        return mem[0][colors.length - 1];
    }
}
    public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int i = 1;
        int k = 0;
        while((line = br.readLine())!=null) {
            if (i%2==0) {
                if (k>0) {
                    String[] parts = line.split(" ");
                    int[] input = new int[parts.length];
                    for (int j = 0; j < parts.length; j++) {
                        input[j] = Integer.parseInt(parts[j]);
                    }
                    System.out.println(new Mixtures().minimumSmoke(input));
                }
            } else {
                k = Integer.parseInt(line);
            }
            i++;

        }

    }
}

