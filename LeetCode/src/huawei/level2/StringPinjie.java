package huawei.level2;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定 M(0<M<=30)个字符（a-z），从中取出任意字符（每个字符只能用一次）拼接成长度为 N(0<N<=5)的字符串，要求相同的字符不能相邻，计算出给定的字符列表能拼接出多少种满足条件的字符串，输入非法或者无法拼接出满足条件的字符串则返回 0。
 *
 * 输入描述：
 * 给定的字符列表和结果字符串长度，中间使用空格（" "）拼接
 *
 * 输出描述：
 * 满足条件的字符串个数
 *
 * 示例1:
 * 输入
 * abc 1
 *
 * 输出
 * 3
 * 说明：给定的字符为 a,b,c，结果字符串长度为 1，可以拼接成 a,b,c，共 3 种
 *
 * 示例2:
 * 输入
 * dde 2
 *
 * 输出
 * 2
 * 说明：给定的字符为 dde，结果字符串长度为 2，可以拼接成 de,ed，共 2 种
 */
import java.util.*;

public class StringPinjie {

    public static int countValidStrings(String chars, int N) {
        if (N > chars.length()) return 0;
        int[][] dp = new int[N + 1][chars.length() + 1];
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : chars.toCharArray()) {
            uniqueChars.add(c);
        }

        // 初始化
        for (int i = 0; i <= chars.length(); ++i) {
            dp[1][i] = uniqueChars.size();
        }

        // 动态规划
        for (int len = 2; len <= N; ++len) {
            for (int used = 1; used <= chars.length(); ++used) {
                // 不使用第 used 个字符的情况
                dp[len][used] = dp[len][used - 1];
                // 使用第 used 个字符的情况，需要保证前一个字符不是当前字符
                if (used >= 2 && chars.charAt(used - 2) != chars.charAt(used - 1)) {
                    dp[len][used] += dp[len - 1][used - 2];
                }
            }
        }

        return dp[N][chars.length()];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            System.out.println(0);
            return;
        }

        String chars = parts[0];
        int N = Integer.parseInt(parts[1]);

        if (N <= 0 || N > 5 || chars.length() > 30) {
            System.out.println(0);
        } else {
            System.out.println(countValidStrings(chars, N));
        }
    }
}
