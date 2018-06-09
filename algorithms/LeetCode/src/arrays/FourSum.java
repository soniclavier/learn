package arrays;

import com.vishnuviswanath.algorithm.rec.Tuple;

import java.util.*;


/**
 * Created by vviswanath on 4/6/18.
 */
public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<Tuple<Integer, Integer>> numTups = new ArrayList<>();
        Map<Integer, Integer> numTupsMap = new HashMap<>();
        for(int num : nums) {
            if (numTupsMap.containsKey(num)) {
                int c = numTupsMap.get(num);
                numTupsMap.put(num, c + 1);
            } else {
                numTupsMap.put(num, 1);
            }
        }

        for(int key: numTupsMap.keySet()) {
            numTups.add(new Tuple<Integer, Integer>(key, numTupsMap.get(key)));
        }
        List<List<Integer>> result = new ArrayList<>();
        fourSumHelper(numTups, 0, target, new LinkedList<>(), result);

        return result;
    }

    private static void fourSumHelper(List<Tuple<Integer, Integer>> numsTups,
                                      int numsUsed,
                                      int target,
                                      LinkedList<Integer> prevNums,
                                      List<List<Integer>> result) {
        if (numsUsed == 4) {
            if (target == 0) {
                result.add((LinkedList)prevNums.clone());
            }
            return;
        }

        for (Tuple t: numsTups) {
            if (canTakeFromTuple(t)) {
                int n = takeFromTuple(t);
                prevNums.add(n);
                fourSumHelper(numsTups, numsUsed + 1, target - n, prevNums, result);
                prevNums.remove(prevNums.size() - 1);
                putBackToTuple(t);
            }
        }


    }

    private static Integer takeFromTuple(Tuple<Integer, Integer> t) {
        t.t2 = t.t2 - 1;
        return t.t1;
    }

    private static boolean canTakeFromTuple(Tuple<Integer, Integer> t) {
        return t.t2 > 0;
    }

    private static void putBackToTuple(Tuple<Integer, Integer> t) {
        t.t2 = t.t2 + 1;
    }

}

