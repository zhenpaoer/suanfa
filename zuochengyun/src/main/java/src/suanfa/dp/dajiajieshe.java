package src.suanfa.dp;

public class dajiajieshe {
    public static void main(String[] args) {
        System.out.println(rob1(new int[]{1,2,3,1}));
        System.out.println(rob1(new int[]{2,7,9,3,1}));
        System.out.println(rob1(new int[]{2,1,1,2}));
    }
    public static int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;

        for(int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i-2]);
        }
        return dp[n];
    }

    public static int rob1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = nums[0];

        for(int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i-1]);
        }
        return dp[n];
    }

    public static int rob2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || (i-1) == j || (j-1) ==i){
                    continue;
                }else {
//                    max = Math.max(max,nums[i]+nums[j]);
                    dp[i][j] = Math.max(dp[i][j-1],dp[i][j-2] + nums[j]);
                }

            }
        }
        return dp[n][n];
    }
}
