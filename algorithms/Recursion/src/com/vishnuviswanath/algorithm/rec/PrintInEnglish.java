package com.vishnuviswanath.algorithm.rec;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vviswanath on 4/24/18.
 */
public class PrintInEnglish {

    static Map<Integer, String> lessThanTwenty = new HashMap<>();
    static Map<Integer, String> tillHundrend = new HashMap<>();
    static Map<Integer, String> moreThanHundred = new HashMap<>();

    public static void initMaps() {
        lessThanTwenty.put(0, "");
        lessThanTwenty.put(1, "one");
        lessThanTwenty.put(2, "two");
        lessThanTwenty.put(3, "three");
        lessThanTwenty.put(4, "four");
        lessThanTwenty.put(5, "five");
        lessThanTwenty.put(6, "six");
        lessThanTwenty.put(7, "seven");
        lessThanTwenty.put(8, "eight");
        lessThanTwenty.put(9, "nine");
        lessThanTwenty.put(10, "ten");
        lessThanTwenty.put(11, "eleven");
        lessThanTwenty.put(12, "twelve");
        lessThanTwenty.put(13, "thirteen");
        lessThanTwenty.put(14, "fourteen");
        lessThanTwenty.put(15, "fifteen");
        lessThanTwenty.put(16, "sixteen");
        lessThanTwenty.put(17, "seventeen");
        lessThanTwenty.put(18, "eighteen");
        lessThanTwenty.put(19, "nineteen");


        tillHundrend.put(2, "twenty");
        tillHundrend.put(3, "thirty");
        tillHundrend.put(4, "fourty");
        tillHundrend.put(5, "fifty");
        tillHundrend.put(6, "sixty");
        tillHundrend.put(7, "seventy");
        tillHundrend.put(8, "eighty");
        tillHundrend.put(9, "ninety");

        moreThanHundred.put(2, "hundred");
        moreThanHundred.put(3, "thousand");
        moreThanHundred.put(6, "million");
        moreThanHundred.put(9, "billion");
    }
    public static void printInEnglish(int n) {
        if (n == 0) System.out.println("zero");
        initMaps();
        int digits = 0;
        int t = n;
        while(t > 0) {
            digits++;
            t /= 10;
        }
        System.out.println(n+" = "+printInEnglishHelper(n, digits));
    }

    public static String printInEnglishHelper(int n, int digits) {
        if (n < 20) {
            return lessThanTwenty.get(n);
        }
        if (n >=20 && n <=99) {
            int ones = n%10;
            int tens = n/10;
            return tillHundrend.get(tens) +" "+lessThanTwenty.get(ones);
        }
        int excluded = 1;
        int tempDigits = digits;
        while(!moreThanHundred.containsKey(tempDigits - 1)) {
            excluded++;
            tempDigits--;
        }

        String higher = moreThanHundred.get(tempDigits - 1);
        int pre =  n / (int) Math.pow(10, digits - excluded);
        String preString = printInEnglishHelper(pre, excluded);

        int nextNum = n % (int) Math.pow(10,(digits - excluded));
        return preString +" "+ higher +" "+ printInEnglishHelper(nextNum, digits - excluded);
     }

     public static void main(String[] args) {
        printInEnglish(1);
        printInEnglish(12);
        printInEnglish(123);
        printInEnglish(1234);
        printInEnglish(12345);
        printInEnglish(123456);
        printInEnglish(1234567);
        printInEnglish(12345670);
     }
}
