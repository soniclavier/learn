import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishnu on 6/5/16.
 */
public class ContainsDuplicateII {

    public static void main(String[] args) {
        SolutionDup s = new SolutionDup();
        System.out.println(s.containsNearbyDuplicate(new int[]{3,2,1,9,2,1,4},2));
    }


}

class SolutionDup {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> dist = new HashMap<Integer,Integer>();
        for(int i =0;i<nums.length;i++) {
            if (!dist.containsKey(nums[i]))
                dist.put(nums[i],i);
            else {
                int curr = dist.get(nums[i]);
                if (i -  curr <= k)
                    return true;
                else {
                    dist.put(nums[i],i);
                }
            }
        }
        return false;
    }
}
