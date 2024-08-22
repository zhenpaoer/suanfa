package src.suanfa.dp.coins;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
//限定面值和每种面值的张数
public class Coins3 {
    public static void main(String[] args) {
        int[] coins = new int[]{1,2,1,1,2,1,2};

        System.out.println(change(coins,4));
    }

    public static boolean change(int[] coins,int amount){
        HashMap<Integer,Integer> count = new HashMap<>();
        for (int coin : coins) {
            if (count.containsKey(coin)) {
                count.put(coin,count.get(coin) + 1);
            }else {
                count.put(coin,1);
            }
        }
        int[] zhangs = new int[count.size()];
        int[] coins2 = new int[count.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : count.entrySet()) {
            coins2[index] = integerIntegerEntry.getKey();
            zhangs[index] = integerIntegerEntry.getValue();
            index++;
        }
        int res = way1(coins2, zhangs,0, amount);
        int res1 = dp1(coins2,  zhangs,amount);
        int res2 = dp2(coins2, zhangs,amount);
        int res3 = dp3(coins2, zhangs,amount);
        System.out.println(res3);
        return res == res1 && res1 == res2 && res2 == res3;
    }

    public static int way1(int[] coins,int zhangs[],int index ,int rest){
        if(rest < 0){
            return 0;
        }
        if(index == coins.length){
            return rest == 0? 1:0;
        }
        int ways = way1(coins,zhangs,index+1,rest);
        for(int zhang = 1;  zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++){
            ways += way1(coins,zhangs,index+1,rest - zhang * coins[index] );
        }
        return ways;
    }

    public static int dp1(int[] coins,int zhangs[],int amount){
        int N = coins.length;
        int[][] dp = new int[N+1][amount+1];
        dp[N][0] = 1;
        for(int index = N-1; index >= 0;index --){
            for (int rest = 0;rest <= amount;rest++){
                dp[index][rest] = dp[index+1][rest];
                if(rest - coins[index] >= 0){
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if(rest - coins[index] * (zhangs[index] + 1) >= 0 ){
                    dp[index][rest] -= dp[index+1][rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }

        return dp[0][amount];
    }

    //压缩成一维空间
    public static int dp2(int[] coins,int zhangs[],int amount){
        int N = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int index = N-1; index >= 0;index --){
            for (int rest = 0;rest <= amount;rest++){
                if(rest - coins[index] >= 0){
                    dp[rest] += dp[rest - coins[index]];
                }
                if(rest - coins[index] * (zhangs[index] + 1) >= 0 ){
                    dp[rest] -= dp[rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }

        return dp[amount];
    }

    //与dp2近似
    public static int dp3(int[] coins,int zhangs[],int amount){
        int N = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int index = 0; index < N;index ++) {
            int coin = coins[index];
            for (int rest = coin; rest <= amount ; rest++){
                dp[rest] += dp[rest - coin];
                if(rest - coins[index] * (zhangs[index] + 1) >= 0 ){
                    dp[rest] -= dp[rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }
        return dp[amount];
    }
}
