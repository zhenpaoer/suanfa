/**
 * 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 输入:
 * A: [1,2,3,2,1]  B: [3,2,1,4,7]
 * 输出: 3
 * 解释:长度最长的公共子数组是 [3, 2, 1]。
 */
public class MaximumLengthofRepeatedSubarray_718 {

	// todo 注意子数组和子序列的区别 如果是子序列的话 递推公式就是 ： dp[i][j] = max(dp[i-1][j-1]+(A[i-1] == B[j-1]?1:0),dp[i-1][j],dp[i][j-1])
	public static int findLength(int[] A, int[] B) {
		if (A.length == 0 || B.length == 0) {
			return 0;
		}
		int[][] dp = new int[A.length+1][B.length+1];
		int result = 0;
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
					result = Math.max(result, dp[i][j]);
				}
			}
		}
		return result;
	}
	public static int findLength1(int[] A, int[] B) {
		int max = 0;
		int[] dp = new int[B.length + 1];
		for (int i = 1; i <= A.length; i++) {
			for (int j = B.length; j >= 1; j--) {
				if (A[i - 1] == B[j - 1])
					dp[j] = dp[j - 1] + 1;
				else
					dp[j] = 0;
				max = Math.max(max, dp[j]);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = {1,2,3,2,1};
		int[] b = {3,2,1,4,7};
		System.out.println(findLength(a, b));
		System.out.println(findLength1(a, b));
	}
}
