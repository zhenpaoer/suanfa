package huawei.level2;

/**
 * 中秋节，公司分月饼，m 个员工，买了 n 个月饼，m<=n，每个员工至少分 1 个月饼，但可以分多个，单人分到最多月饼的个数为 Max1，单人分到第二多月饼的个数是 Max2，Max1-Max2<=3,单人分到第 n-1 多月饼个数是 Max(n-1)，
 * 单人分到第 n 多月饼的个数是 Max(n)，Max(n-1)-Max(n)<=3。请问有多少种分月饼的方法？
 * 每一行输入 m、n，表示 m 个员工，n 个月饼，m<=n
 * 输出有多少种分月饼的方法
 *
 * 输入
 *
 * 2  4
 *
 * 输出
 *
 * 2
 *
 * -------------------------------------------------------------------------------------------------------------------
 *
 * > 说明
 * > 分法有 2 种：
 * > 4 = 1 + 3
 * > 4 = 2 + 2
 * > 注意：1+3 和 3+1 算一种分法
 *
 * 输入
 * 3   5
 * 输出
 * 2
 *
 */
public class Fenyuebing {
    public static void main(String[] args) {
        System.out.println(getMoonCake(2, 4));
    }
    public static int getMoonCake(int m, int n) {
        if (m > n) {
            return 0;
        }
        int p = n - m;
        int count = 0;

        for (int i = 0; i < p; i++) {
            count = count + distribute(m - 1, p - i, i);
        }
        System.out.println(count);
        return count;
    }

    private static int distribute(int m, int p, int k) {
        if (p <= 0) return 0;
        if (m <= 0) return 0;
        if (m == 1) {
            if (p >= k && p <= k + 3) {
                return 1;
            }
            return 0;
        }

        int ncount = 0;

        for (int knext = k; knext <= k + 3; knext++) {
            ncount = ncount + distribute(m - 1, p - knext, knext);
        }
        return ncount;
    }
}
