package com.vishnuviswanath.algorithm.rec;

public class Count2s {

    public static int count2s(int n) {
        int c = 0;
        for(int i = 0; i <= n; i++) {
            c += count2sInN(i);
        }
        return c;
    }

    private static int count2sInN(int n) {
        int c = 0;
        while(n > 0) {
            if (n % 10 == 2) c++;
            n /= 10;
        }
        return c;
    }

    /**
     * wrong!
     * @param n
     * @return
     */
    public static int count2sV2(int n) {
        int zeros = 0;
        int t = n/10;
        while(t > 0) {
            zeros++;
            t /= 10;
        }

        return count2sHelper(n, zeros);
    }

    private static int count2sHelper(int n, int zeros) {
        int c = 0;
        if (zeros < 0) return 0;
        if (zeros == 0) {
            if (n > 2) return 1;
            else return 0;
        }

        int h = (int) Math.pow(10, zeros);
        int m = n/h;

        int nn = n - m * h;

        c += m * h/10;
        if (m > 2) c += h;
        if (m == 2) c += nn;

        c += count2sHelper(nn, zeros - 1 );

        return c;

    }


    public static void main(String[] args) {
        System.out.println(count2s(112));
        System.out.println(count2sV2(112));
    }
}
