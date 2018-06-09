package com.vishnuviswanath.algorithm.rec;

/**
 * Created by vviswanath on 4/1/18.
 */
public class ValidParanthesis {

    public static void validParenthesis(int n) {
        if (n <= 0) return;

        validParenthesis(n, 0, 0, "");
    }

    private static void validParenthesis(int n, int l, int r, String p) {
        if (n < 0) return;
        if (n == 0 && l == r) {
            System.out.println(p);
        } else {
            if (n > 0) validParenthesis(n - 1, l + 1, r, p + "(");
            if (l > r) validParenthesis(n, l, r + 1, p +")");
        }
    }

    public static void main(String[] args) {
        validParenthesis(3);
    }
}
