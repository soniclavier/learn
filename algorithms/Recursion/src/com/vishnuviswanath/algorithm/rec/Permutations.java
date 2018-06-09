package com.vishnuviswanath.algorithm.rec;

import java.util.*;

/**
 * Created by vviswanath on 3/31/18.
 */
public class Permutations {

    public static void permutation(String a) {
        //permuteHelper(a, "", a.length(), new HashSet<>());
        Map<Character, Integer> m = new HashMap<>();
        for(char ch : a.toCharArray()) {
            if (m.containsKey(ch)) {
                m.put(ch, m.get(ch) + 1);
            } else {
                m.put(ch, 1);
            }
        }

        List<Tuple<Character, Integer>> from = new ArrayList<>();
        for(char ch: m.keySet()) {
            int c = m.get(ch);
            from.add(new Tuple<Character, Integer>(ch, c));
        }

        permuteHelper(from, "", a.length());
    }

    private static void permuteHelper(String from, String prefix, int len) {
        if (prefix.length() == len) {
            System.out.println(prefix);
        } else {
           for (int i = 0; i < from.length(); i++) {
               String tail = "";
               if (from.length() > i) {
                   tail = from.substring(i + 1);
               }
               permuteHelper(from.substring(0, i) + tail, prefix + from.charAt(i), len);
           }
        }
    }

    private static void permuteHelper(String from, String prefix, int len, Set<Tuple<String, String>> mem) {
        Tuple tup = new Tuple(from, prefix);
        if (mem.contains(tup)) return;
        mem.add(tup);

        if (prefix.length() == len) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < from.length(); i++) {
                String tail = "";
                if (from.length() > i) {
                    tail = from.substring(i + 1);
                }
                permuteHelper(from.substring(0, i) + tail, prefix + from.charAt(i), len, mem);
            }
        }

    }

    private static void permuteHelper(List<Tuple<Character, Integer>> from, String prefix, int len) {
        if (prefix.length() == len) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < from.size(); i++) {
                Tuple tup = from.get(i);
                Character ch = takeFromTup(tup);
                if (ch != null) {
                    permuteHelper(from, prefix + ch, len);
                }
                putBackTup(tup);
            }
        }
    }

    private static Character takeFromTup(Tuple<Character, Integer> tup) {
        if (tup.t2 == 0) return null;
        tup.t2 = tup.t2 - 1;
        return tup.t1;
    }

    private static void putBackTup(Tuple<Character, Integer> tup) {
        tup.t2 = tup.t2 + 1;
    }

    public static void main(String[] args) {
        permutation("aba");
    }
}

