/**
 * Created by zhangzhen on 2020/5/1
 */

import static sun.swing.MenuItemLayoutHelper.max;

/**
 *
 * 给定一个数组nums，求最大和的连续子数组
 * [-2,1,-3,4,-1,2,1,-5,4] 输出4，-1，2,1 最大和为6
 **/
public class maxSubStr {

	public static int maxSubStr(int[] nums){
		if (nums.length==0||nums == null) return 0;
		int n = nums.length;
		int arr[] = new int[n];
		arr[0] = nums[0];
		int resutl = arr[0];
		System.out.print(arr[0]+" ");
		for (int i=1;i<n;++i){
			arr[i] = Math.max(arr[i-1],0)+nums[i];
			resutl = Math.max(resutl,arr[i]);
			System.out.print(arr[i]+" ");
		}
		return resutl;
	}

	public static int maxSubStr2(int[] nums){
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int max = 0;
		for (int i = 1; i<nums.length;i++){
			dp[i] = Math.max(dp[i-1] + nums[i],dp[i]);
			max = Math.max(dp[i],max);
		}
		return max;
	}

	public static void main(String[] args) {
		int nums[] = {-2,1,-3,4,-1,2,1,-5,4};
		final int i = maxSubStr2(nums);
		System.out.println(i);
	}
}
