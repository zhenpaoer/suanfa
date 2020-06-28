/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */

public class MinimumSizeSubarraySum_209 {

	//利用滑动窗口 头指针i,尾指针j，先让j去加 符合条件后减少i对应的下标值，这也就是为什么是j-i+1
	public static int minSubArrayLen(int s, int[] nums) {
		int i = 0;
		int len = 0;
		int sum = 0;
		for (int j = 0;j < nums.length;j++){
			sum += nums[j];
			while ( sum >= s){
				len = len == 0?j-i+1:Math.min(len,j-i+1);
				sum -= nums[i++];
			}
		}
		return len;
	}

	public static void main(String[] args) {
		int s = 7;
		int[] nums = {2,3,1,2,4,3};
		System.out.println(minSubArrayLen(s, nums));
	}
}
