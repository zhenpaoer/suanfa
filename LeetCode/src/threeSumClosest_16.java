/**
 * Created by zhangzhen on 2020/5/5
 */

import java.util.Arrays;

/**
 * @Date 2020/5/5
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 **/
public class threeSumClosest_16 {
	public static void main(String[] args) {
		int[] nums = {-1,2,1,-4};
		int target = 1;
		System.out.println(threeSumClosest(nums, target));
	}
	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int res = nums[0] + nums[1] + nums[2];
		for (int i = 0; i < nums.length; i++) {
			int j = i+1;
			int k = nums.length - 1;
			while (j<k){
				int sum = nums[i] + nums[j] + nums[k];
				if (Math.abs(target - sum ) < Math.abs(target - res )){
					res = sum;
				}
				if (sum > target){
					k--;
				}
				else if (sum < target){
					j++;
				}
				else return res;
			}
		}

		return res;



	}
}
