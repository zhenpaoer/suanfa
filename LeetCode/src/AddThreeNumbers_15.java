/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 **/
public class AddThreeNumbers_15 {
	//a+b=-c
	public static void main(String[] args) {
		int[] nums = {-1, 0, 1, 2, -1, -4};
//		System.out.println(Arrays.toString(method_1(nums)));
		System.out.println(method_2(nums));
	}

	//方式一：不太对
	private static int[] method_1(int[] nums) {

		int[] arr = new int[3];
		HashMap<Integer,Integer> hashMap = new HashMap<>();
		int c = 0;
		// -1+0=-1
		for (int i = 0; i < nums.length; i++) {
			c = nums[i];
			for (int j = 1;j<nums.length-1;j++){
				hashMap.put(0-c-nums[j],j);//key为a,value为a的坐标
				if (hashMap.containsKey(-c-nums[j])){
					arr[0] = nums[i];//c
					arr[1] = -c-nums[j];
					arr[2] = nums[j];
					System.out.println(Arrays.toString(arr));
					break;
				}

			}
			hashMap.clear();
		}
		return arr;
	}

	//方式二：正确的
	private static List<List<Integer>> method_2(int[] nums){
		List<List<Integer>> list = new ArrayList<>();
		if (nums.length==0 ) return list;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			//去重
			if (i>0 && nums[i] == nums[i-1]){
				continue;
			}
			int target = -nums[i];
			int j = i+1; //左指针
			int k = nums.length - 1; //右指针
			while (j<k){
				if (nums[j]+nums[k] == target){
					List<Integer> subList = new ArrayList<>();
					subList.add(nums[i]);
					subList.add(nums[j]);
					subList.add(nums[k]);
					list.add(subList);
					j++;k--;
					//去重
					while (j<nums.length && nums[j] == nums[j-1]){
						j++;
					}
					while (k>j && nums[k] == nums[k+1]) {
						k--;
					}
				}else if (nums[j]+nums[k] > target){
					k--;
				}else {
					j++;
				}
			}
		}
		return list;
	}
}
