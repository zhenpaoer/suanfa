/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 * @ClassName trainStole
 * @Description: TODO
 * @Author zhangzhen
 * @Date 2020/5/1 
 * @Version V1.0
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 输入: [2,7,9,3,1]输出: 12解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 *
 *
 **/
public class trainStole {
	private static int stole(int[] nums) {
		int money = 0;
		int[] dp = new int[nums.length+1];
		dp[1]=nums[0];
		for (int i = 2;i<=nums.length;++i){
			dp[i] = Math.max(dp[i-2]+nums[i-1],dp[i-1]);
			money = Math.max(dp[i],money);
		}

		return money;
	}

	/**
	 *前面那道题目的 follow up，问的是如果这些房子的排列方式是一个圆圈，其余要求不变，问该如何处理。
	 *
	 * 房子排列方式是一个圆圈意味着之前的最后一个房子和第一个房子之间产生了联系，
	 * 这里有一个小技巧就是我们线性考虑 [0, n – 2] 和 [1, n – 1]，然后求二者的最大值。
	 *
	 * 其实这么做的目的很明显，把第一个房子和最后一个房子分开来考虑。实现上面我们可以直接使用之前的实现代码。
	 *
	 * 这里有一个边界条件就是，当只有一个房子的时候，我们直接输出结果即可。
	 **/
	public static int rob(int[] nums){
		if (nums == null || nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		int n = nums.length;

		//把数组分为两个，一个是从0到n-1，一个是从1到n，再去找最大值
		System.out.println(stole(Arrays.copyOfRange(nums, 0, n - 1)));
		System.out.println(stole(Arrays.copyOfRange(nums, 1, n )));


		return
				Math.max(
				stole(Arrays.copyOfRange(nums, 0, n - 1)),
				stole(Arrays.copyOfRange(nums, 1, n))
		);
	}

	public static void main(String[] args) {
		int nums[] = {2,7,9,3,19};
		System.out.println(rob(nums));
	}


}
