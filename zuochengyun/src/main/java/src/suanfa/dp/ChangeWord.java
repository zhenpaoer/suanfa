package src.suanfa.dp;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 */
public class ChangeWord {
    public static void main(String[] args) {
        System.out.println(proccess("horse", "ros"));
        System.out.println(proccess2("horse", "ros"));
        System.out.println(proccess3("horse", "ros"));
    }

    private static int proccess(String word1,String word2){

        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = dp[i-1][0]+1;
        }
        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i] = dp[0][i-1]+1;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt( i -1 ) == word2.charAt( j-1)){
                    dp[i][j] = dp[i -1 ][j -1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    /**
     * 错误示范 dp[i-1][j-1]的值没正确获取到
     * @param word1
     * @param word2
     * @return
     */
    private static int proccess2(String word1,String word2){
        int[] dp = new int[word2.length()];
        for (int i = 1; i < word2.length(); i++) {
            dp[i] = dp[i-1] + 1;
        }
        for (int i = 1; i < word1.length(); i++) {
            dp[0] = dp[0] + 1;
            for (int j = 1; j < word2.length(); j++) {
                if (word1.charAt( i -1 ) == word2.charAt( j-1)){
                    dp[j] = dp[j-1];
                }else {
                    dp[j] = Math.min(Math.min(dp[j],dp[j-1]),dp[j-1]+1) +1;
                }
            }
        }
        return dp[word2.length()-1];
    }

    /**
     * 优化后的 一维数组
     * @param word1
     * @param word2
     * @return
     */
    private static int proccess3(String word1,String word2){
        int[] dp = new int[word2.length()];
        for (int i = 0; i < word2.length(); i++) {
            dp[i] = i;
        }
        for (int i = 1; i < word1.length(); i++) {
            int pre = dp[0];
            dp[0] = i;
            for (int j = 1; j < word2.length(); j++) {
                if (word1.charAt( i - 1 ) == word2.charAt( j-1)){
                    dp[j] = pre;
                }else {
                    dp[j] = Math.min(Math.min(dp[j],dp[j-1]),pre) +1;
                }
            }
        }
        return dp[word2.length()-1];
    }
}
