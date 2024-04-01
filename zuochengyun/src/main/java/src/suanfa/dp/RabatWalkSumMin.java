package src.suanfa.dp;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * **说明：**每次只能向下或者向右移动一步。
 * 举例：
 * 输入:
 * arr = [
 *   [1,3,1],
 *   [1,5,1], 1 4 5   276
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class RabatWalkSumMin {
    public static void main(String[] args) {
        int[][] arr = new int[3][3];
        // 初始化数组
        arr[0][0] = 1;
        arr[0][1] = 3;
        arr[0][2] = 1;
        arr[1][0] = 1;
        arr[1][1] = 5;
        arr[1][2] = 1;
        arr[2][0] = 4;
        arr[2][1] = 2;
        arr[2][2] = 1;
        System.out.println(proccess(arr));
        System.out.println(proccess2(arr));
    }
    
    private static int proccess(int[][] arr){
        int[][] dp = new int[arr[0].length][arr.length];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < arr[0].length; i++) {
            dp[0][i] = dp[0][i-1] + arr[0][i];
        }
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = dp[i-1][0] + arr[i][0];
        }
//        dp[m][n] = Math.min(dp[m][n-1],dp[m-1][n]);
        for (int i = 1; i < arr[0].length; i++) {
            for (int j = 1; j < arr.length ; j++) {
                dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j]) + arr[i][j];
            }
        }
        return dp[arr[0].length-1][arr.length-1];
    }

    /**
     *
     *         由于i行不需要i-1的行 因此可以干掉，只用一维数组存结果
     *      将二维数组 转成 一维数组
     *      1 4 5
     *  *   2 7 6
     *  *   6 8 7
     * @param arr
     * @return
     */
    private static int proccess2(int[][] arr){
       int[] dp = new int[arr[0].length];
       dp[0] = arr[0][0];
       for (int i = 1; i < arr[0].length; i++) {
            dp[i] = dp[i-1] + arr[0][i];
       }
       for (int i = 1; i < arr.length; i++) {
            dp[0] = dp[0] + arr[i][0];
            for (int j = 1; j < arr[0].length; j++) {
                dp[j] = Math.min(dp[j-1],dp[j])+ arr[i][j];
            }

       }
       return dp[arr[0].length-1];
    }
}
