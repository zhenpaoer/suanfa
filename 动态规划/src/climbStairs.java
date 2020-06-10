/**
 * Created by zhangzhen on 2020/5/1
 */

/**
 * n个台阶，每次走1或者2次，有多少种走法
 **/
public class climbStairs {

	public static int climbStairs(int n){
		int arr[] = new int[n+1];
		arr[0]=0;
		arr[1]=1;
		arr[2]=2;
		for (int i=3;i<=n;i++){
			arr[i] = arr[i-1]+arr[i-2];
		}
		return arr[n];
	}


	public static int climbStairs2(int n){
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(climbStairs2(6));
	}
}
