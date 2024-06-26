package src.suanfa.dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 */
public class RabatWalk2 {
    public static void main(String[] args) {
        int[][] ints = new int[3][3];
        ints[0] = new int[]{0,0,0};
        ints[1] = new int[]{0,1,0};
        ints[2] = new int[]{0,0,0};
        System.out.println(uniquePathsWithObstacles(ints));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = (obstacleGrid[0][0] == 1) ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1){
                    dp[j] = 0;
                }else if (j-1 >= 0 && obstacleGrid[i][j] == 0){
                    dp[j] = dp[j-1] + dp[j];
                }

            }
        }
        return dp[n-1];
    }
}
