package src.suanfa.dp.coins;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
// lc https://leetcode.cn/problems/coin-change/description/?envType=study-plan-v2&envId=dynamic-programming
public class MinCoins {

    public static void main(String[] args) {
//        int[] coins = new int[]{1,2,5};
//        int amount = 11;
        int[] coins = new int[]{2};
        int amount = 3;
//        int[] coins = new int[]{1};
//        int amount = 0;
        System.out.println(coinChange(coins,amount));
    }

    public static boolean coinChange(int[] coins, int amount) {
        int res = proccess(coins,0,amount);
        res = res == Integer.MAX_VALUE ? -1 : res;
        System.out.println(res);
        int res1 = dp1(coins,amount);
        System.out.println(res1);
        int res2 = dp2(coins,amount);
        System.out.println(res2);
        return res == res1 && res1 == res2;
    }

    public static int proccess(int[] coins,int index , int amount){
        if(amount < 0){
            return 0;
        }
        if(index == coins.length){
            return amount == 0 ? 1:Integer.MAX_VALUE;
        }
        int res = proccess(coins,index+1,amount);
        for(int zhang = 0;zhang * coins[index] <= amount ; zhang++){
            int next = proccess(coins,index+1,amount - zhang * coins[index]);
            if(next != Integer.MAX_VALUE){
                res = Math.min(res,next+zhang);
            }
        }
        return res ;

    }

    public static int dp1(int[] coins, int amount){
        int N = coins.length;
        int[][] dp = new int[N+1][amount+1];

        dp[N][0] = 1;
        for (int i = 1; i <= amount; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        for(int index = N-1;index >= 0;index--){
            for(int rest = 0;rest <= amount;rest++){
                dp[index][rest] = dp[index + 1][rest];
                if((rest - coins[index] >= 0) && dp[index][rest - coins[index]] != Integer.MAX_VALUE){
                    dp[index][rest] = Math.min( dp[index][rest],dp[index][rest - coins[index]] + 1);
                }
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 :dp[0][amount] ;
    }


    public static int dp2(int[] coins, int amount){
        int N = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
         dp[0] = 1;
        for(int index = N-1;index >= 0;index--){
            for(int rest = 0;rest <= amount;rest++){
                if((rest - coins[index] >= 0) && dp[rest - coins[index]] != Integer.MAX_VALUE){
                    dp[rest] = Math.min( dp[rest],dp[rest - coins[index]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
