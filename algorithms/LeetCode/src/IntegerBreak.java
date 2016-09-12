import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishnu on 6/5/16.
 */
public class IntegerBreak {
}

class SolutionIntBreak {


    Map<Integer,Integer> memo = new HashMap<Integer,Integer>();
    public int integerBreak(int n) {
        if (memo.containsKey(n)) return memo.get(n);
        int max = Integer.MIN_VALUE;
        if (n < 2)
            return n;
        for(int i=1;i<=n;i++) {
            int local = Math.max(i*integerBreak(n-i),i*(n-i));
            if (local > max)
                max = local;
        }
        memo.put(n,max);
        return max;
    }

    public static void main(String[] args) {
        SolutionIntBreak sl = new SolutionIntBreak();
        System.out.println(sl.integerBreak(28));
    }
}
