package base;

/**
 * Created by vishnu on 2/19/16.
 */
public class CoinChanges {

    public static int minCoins(int[] coins,int S) {
        int[] mem = new int[S+1];
        int[] s = new int[S+1];
        for(int i=1;i<=S;i++) {
            int min = Integer.MAX_VALUE;
            int coinUsed = -1;
            for(int j = 0;j<coins.length;j++) {
                if (i-coins[j]<0)
                    continue;
                int cost = 1+mem[i-coins[j]];
                if (cost < min) {
                    min = cost;
                    coinUsed = coins[j];
                }
            }
            mem[i]=min;
            s[i]=coinUsed;

        }
        for(int i=0;i<mem.length;i++)
            System.out.print(mem[i]+" ");
        System.out.println();
        for(int i=0;i<s.length;i++)
            System.out.print(s[i]+" ");
        System.out.println();
        return mem[S];
    }

    public static void main(String[] args) {
        System.out.println(minCoins(new int[]{1,5,12,25},16));
    }
}
