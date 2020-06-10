/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 **/
public class AddTwoNumbers_1 {

	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 9;
//		System.out.println(Arrays.toString(method_1(nums, target)));
//		System.out.println(Arrays.toString(method_2(nums, target)));
		System.out.println(method_3(nums, target));
	}



	//方法一：自己做的
	private static int[] method_1(int[] nums, int target) {
		int[] arr = new int[2];
		for (int i = 0;i<nums.length;i++){
			for (int j = i+1;j<nums.length;j++){
				if ((nums[i] + nums[j] ) == target ) {
					arr[0] = i;
					arr[1] = j;
				}
			}
		}
		return arr;
	}

	//方法二：大牛做法
	private static int[] method_2(int[] nums, int target) {
		int[] arr = new int[2];
		HashMap<Integer,Integer> hashMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (hashMap.containsKey(nums[i])){
				arr[0] = hashMap.get(nums[i]);
				arr[1] = i;
				return arr;
			}
			hashMap.put(target - nums[i],i);

		}

		return arr;
	}

	//方法三：根据三数求和的思路  自己写二数之和 使用左右指针
	private static List<Integer>  method_3(int[] nums, int target){
		List<Integer> result = new ArrayList<>();
		Arrays.sort(nums);
		int i = 0;
		int j = nums.length - 1;
		while (i < j) {
			if (nums[i] + nums[j] == target) {
				result.add(i);
				result.add(j);
				return result ;
			} else if (nums[i] + nums[j] > target) {
				j--;
			}else {
				i++;
			}
		}




		return result ;
	}

}
