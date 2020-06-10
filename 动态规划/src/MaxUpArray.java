/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 输入: [10,9,2,5,3,7,101,18]输出: 4 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 **/
public class MaxUpArray {
	//自己实现
	public static int search(int arr[]){
		int length = 0;
		for (int i = 1;i<arr.length;i++){
			if (arr[i]>arr[i-1]){
				length += 1;
			}
		}

		return length;
	}

	//正确的
	public static int search1(int arr[]){
		int length = arr.length;
		int dp[] = new int[length];
		Arrays.fill(dp,1);
		int max = 0;
		for (int i = 0;i<arr.length;i++){
			for (int j = 0;j<i;j++){
				if (arr[j]<arr[i]){
					dp[i] = Math.max(dp[j]+1,dp[i]);
				}
			}
			max = Math.max(max,dp[i]);
			System.out.println(Arrays.toString(dp));
		}
		System.out.println(Arrays.toString(dp));
		return max;
	}

	public static  int search2(int[] arr){
		int[] dp = new int[arr.length];
		Arrays.fill(dp,1);
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			//i是尾指针  j是开始指针
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]){
					dp[i] = Math.max(dp[i-1]+1,dp[i]);
				}
			}
			max = Math.max(dp[i],max);
		}
		return max;
	}

	public static void main(String[] args) {
		int nums[] = {10,9,2,5,3,7,101,18};
		System.out.println(search2(nums));
	}
}
