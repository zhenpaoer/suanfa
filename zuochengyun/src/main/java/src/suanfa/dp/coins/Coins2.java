package src.suanfa.dp.coins;


/**
 * 给定数组arr，arr中所有的值都为正数且不重复
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张
 * 再给定一个整数 aim，代表要找的钱数
 * 求组成 aim 的方法数
 */
//限定面值，不限定每种面值的张数
// lc https://leetcode.cn/problems/coin-change-ii/description/

public class Coins2 {
    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};

        System.out.println(change(coins,5));
    }

    public static boolean change(int[] coins,int amount){
        int res = way(coins, 0, amount);
        int res1 = dp1(coins,  amount);
        int res2 = dp2(coins,  amount);
        System.out.println(res1);
        return res == res1 && res1 == res2;
//        return res == res1 ;
    }

    //递归
    public static int way(int[] coins,int index,int amount){
        if(amount < 0){
            return 0;
        }
        if(index == coins.length){
            return amount == 0? 1:0;
        }
        int ways = way(coins,index+1,amount);
        for(int zhang = 1;zhang * coins[index] <= amount ; zhang++){
            ways += way(coins,index+1,amount - zhang * coins[index]);
        }
        return ways;
    }



    //改成二维数组 张数不是枚举，通过观察表得来的规律   求的值是下面格子的值+左边的格子的值
    public static int dp1(int[] coins,int amount){
        int N = coins.length;
        int[][] dp = new int[N+1][amount+1];
        dp[N][0] = 1;
        for(int index = N-1; index >= 0;index--){
            for (int rest = 0; rest <= amount ; rest++){
                dp[index][rest] = dp[index+1][rest];
                if(rest - coins[index] >= 0){
                    dp[index][rest] += dp[index][rest - coins[index]];
                }

            }
        }
        return dp[0][amount];
    }

    //改成一维数组
    //完全背包需要正序
    public static int dp2(int[] coins,int amount){
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int rest = coin; rest <= amount ; rest++){
                dp[rest] += dp[rest - coin];
            }
        }
        return dp[amount];
    }
}
