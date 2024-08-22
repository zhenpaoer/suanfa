package src.suanfa.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有⼀个背包，背包总的承载᯿量是 Wkg。现在我们有 n 个物品，每个物品的᯿量不等，并且不可
 * 分割。我们现在期望选择⼏件物品，装载到背包中。在不超过背包所能装载᯿量的前提下，如何
 * 让背包中物品的总量最⼤？ 假设这 n 个物品的质量分别 3kg, 4kg, 6kg, 8kg，背包总的承载᯿量
 * 是 10kg。
 */
public class Bag {

    public static void main(String[] args) {
        int[] weight = new int[]{10, 20, 30};
        int[] value= new int[]{60, 100, 120};
        int bag = 50;
        System.out.println(dp1(weight,value,bag));
        System.out.println(dp2(weight,value,bag));
    }

    //01 背包
    public static int dp1(int[] weight,int[] value,int bag){
        int[] dp = new int[bag+1];

        for (int i = 0; i < weight.length ; i++) {
            for (int j = bag;j>=weight[i];j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bag];
    }

    //完全 背包
    public static int dp2(int[] weight,int[] value,int bag){
        int[] dp = new int[bag+1];

        for (int i = 0; i < weight.length ; i++) {
            for (int j = weight[i]; j<=bag;j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bag];
    }
}
