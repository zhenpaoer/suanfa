package src.suanfa.dp; /**
 * Created by zhangzhen on 2020/5/1
 */

/**
 * n个台阶，每次走1或者2次，有多少种走法
 **/
public class climbStairs {
	private static int climbStairs2(int n){
		int[] arr = new int[n+1];
		arr[1] = 1;
		arr[2] = 2;
		for (int i = 3; i <= n; i++) {
			arr[i] =  arr[i-1] + arr[i-2];
		}
		return arr[n];
	}

	/**
	 * 滚动数组，
	 *
	 * 滚动数组是DP中的一种编程思想。简单的理解就是让数组滚动起来，每次都使用固定的几个存储空间，来达到压缩，
	 * 节省存储空间的作用。起到优化空间，主要应用在递推或动态规划中（如01背包问题）。因为DP题目是一个自底向上的扩展过程，我们常常需要用到的是连续的解，前面的解往往可以舍去。所以用滚动数组优化是很有效的。利用滚动数组的话在N很大的情况下可以达到压缩存储的作用。是用时间去换空间的
	 *
	 * 上面这个循环d[i]只依赖于前两个数据d[i - 1]和d[i - 2]; 为了节约空间用滚动数组的做法，可以将整个dp数组压缩成dp[3]
	 * @param n
	 * @return
	 */
	private static int climbStairs3(int n){
		int[] arr = new int[3];
		arr[1] = 1;
		arr[2] = 2;
		for (int i = 2; i < n; i++) {
			arr[0] = arr[1];
			arr[1] = arr[2];
			arr[2] = arr[0] + arr[1];
		}
		return arr[2];
	}

	/**
	 * 矩阵相乘(矩阵快速幂)
	 * Solution5叫做 Binets Method，它利用数学归纳法证明了一下，这里就直接用了，至于怎么想出来的，我也不清楚了。
	 *
	 * 定义一个矩阵 $Q =
	 * 11 10
	 * 11 10
	 * $ ，然后求 f ( n ) 话，我们先让 Q 矩阵求幂，然后取第一行第一列的元素就可以了，也就是
	 * f(n)=Q^n[0][0]
	 * @param n
	 * @return
	 */
	public static int climbStairs(int n) {
		int[][] Q = {{1, 1}, {1, 0}};
		int[][] res = pow(Q, n);
		return res[0][0];
	}
	public static int[][] pow(int[][] a, int n) {
		int[][] ret = {{1, 0}, {0, 1}};
		while (n > 0) {
			//最后一位是 1，加到累乘结果里
			if ((n & 1) == 1) {
				ret = multiply(ret, a);
			}
			//n 右移一位
			n >>= 1;
			//更新 a
			a = multiply(a, a);
		}
		return ret;
	}
	public static int[][] multiply(int[][] a, int[][] b) {
		int[][] c = new int[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
			}
		}
		return c;
	}


	public static void main(String[] args) {
		System.out.println(climbStairs2(10));
		System.out.println(climbStairs(10));
		System.out.println(climbStairs3(10));
	}
}
