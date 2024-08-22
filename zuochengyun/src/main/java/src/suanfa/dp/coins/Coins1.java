package src.suanfa.dp.coins;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */

//每张都认为不同
public class Coins1 {

    public static void main(String[] args) {
        int[] coins = new int[]{1,1,1,2,2,3};

        System.out.println(change(coins,11));
    }

    public static boolean change(int[] coins,int amount){
        int res = way1(coins, 0, amount);
        int res1 = dp1(coins,  amount);
        int res2 = dp2(coins,  amount);
        return res == res1 && res1 == res2;
    }

    public static int way1(int[] coins,int index ,int rest){
        if(rest < 0){
            return 0;
        }
        if(index == coins.length){
            return rest == 0? 1:0;
        }
        int ways = way1(coins,index+1,rest);
        if(rest - coins[index] >= 0){
            ways +=  way1(coins,index+1,rest - coins[index]);
        }
        return ways;
    }

    public static int dp1(int[] coins,int amount){
        int N = coins.length;
        int[][] dp = new int[N+1][amount+1];
        dp[N][0] = 1;
        for(int index = N-1 ; index >= 0;index--){
            for(int rest = 0; rest <= amount;rest++){
                dp[index][rest] = dp[index+1][rest];
                if(rest - coins[index] >= 0){
                    dp[index][rest] +=  dp[index+1][rest - coins[index]];
                }
            }
        }
        return dp[0][amount];
    }

//  一维数组  01背包 需要倒序，否则会被覆盖
    public static int dp2(int[] coins,int amount){
        int[] dp = new int[amount+1];
        dp[0] = 1;
        // 遍历货币数组
        for (int coin : coins) {
            // 反向遍历金额，防止重复使用同一张货币
            for (int rest = amount; rest >= coin; rest--) {
                // 更新dp[j]的值
                dp[rest] += dp[rest - coin];
            }
        }
        return dp[amount];
    }
}
