/**
 *  不同路径 II
 *  一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 */
public class UniquePathsII {
	//动态规划的思路 用dp[i][j]表示第i行j列的位置到终点的路劲条数，由于只能往右走和往下走，所以dp[i][j]的值等于它右边和下面两个格子的值，
	// 即dp[i][j+1]+dp[i+1][j] 障碍物所在的格子dp值为0，需要先将终点dp值设置为1，然后从下往上，从右往左遍历。

	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length; //m是行，n是列
		int[][] dp = new int[m][n];
		dp[m-1][n-1] = 1;
		for(int i=m-1;i>=0;i--){
			for(int j=n-1;j>=0;j--){
				if(obstacleGrid[i][j] == 1) dp[i][j] = 0;
				else{
					dp[i][j] = dp[i][j] + (j+1<n?dp[i][j+1]:0) + (i+1<m?dp[i+1][j]:0);
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[][] arr = {{0,0,0},{0,1,0},{0,0,0}};
		System.out.println(uniquePathsWithObstacles(arr));
	}

}
