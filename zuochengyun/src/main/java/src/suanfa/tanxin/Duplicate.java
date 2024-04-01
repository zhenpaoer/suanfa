package src.suanfa.tanxin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定⼀个区间的集合，找到需要移除区间的最⼩数量，使剩余区间互不᯿叠。 注意:可以认为区间
 * 的终点总是⼤于它的起点。区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互᯿叠。 示例 1: 输⼊:
 * [ [1,2], [2,3], [3,4], [1,3] ] 输出: 1 解释: 移除 [1,3] 后，剩下的区间没有᯿叠。
 * 示例 2: 输⼊: [ [1,2], [1,2], [1,2] ] 输出: 2 解释: 你需要移除两个 [1,2] 来使剩下的区间没有᯿叠。
 * 示例 3: 输⼊: [ [1,2], [2,3] ] 输出: 0 解释: 你不需要移除任何区间，因为它们已经是⽆᯿叠的了。
 */
public class Duplicate {
    public static void main(String[] args) {
        // 初始化区间
        Interval[] intervals = {
                new Interval(1, 2),
                new Interval(3, 5),
                new Interval(4, 7),
                new Interval(8, 10),
                new Interval(9, 11)
        };
        System.out.println("result = " + removeSubDuplicateWithDP(intervals));
        System.out.println("result = " + removeSubDuplicateWithGreedy(intervals));
    }

    /**
     * 判断两区间是否᯿叠, i 区间的起点⽐ j 区间的⼤, 如果 j 区间的终点⽐ i 区间的起点⼤，则᯿叠
     */
    private static boolean isOverlapping(Interval i, Interval j) {
        return j.end > i.start;
    }

    /**
     * 动态规划求解
     * 我们定义 dp[i] 为 从 0 到 第 i 个区间 的 最⼤不重叠区间数,于是我们得出了状态转移⽅程
     * dp[i] = max{dp[j]} + 1, 其中 0 <=j < i 并且需要满⾜⼀个条件 interval[i].start >
     * interval[j].end,即保证 i, j 指向的区间不᯿叠。
     * 则最终的 dp[区间总个数-1] 则为最⼤的连续不᯿叠区间个数，那么 区间总个数 - 最⼤的连续不᯿叠区
     * 间个数不就是最⼩要移除的区间数了
     *
     * 空间复杂度是多少，由于只有⼀个 dp ⼀维数组，所以是 O(n)，时间复杂度呢， 两᯿循环，所以是
     * O(n^2)。可以看到和采⽤递归+备忘录的时间复杂度⼀样，不过之前其实说了很多次，递归容易导致栈
     * 溢出，所以建议还是采⽤动态规划的⽅式来求解。
     */
    private static Integer removeSubDuplicateWithDP(Interval[] intervals) {
        // 将区间按起始点由⼩到⼤进⾏排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        int[] dp = new int[intervals.length];
        Arrays.fill(dp, 0);
        dp[0] = 1; // 将 dp[0] 置为 1， 因为就算所有的区间都᯿叠，则连续不᯿叠区间到少也为 1
        int result = 1;
        for (int i = 1; i < intervals.length; i ++) {
            int max = 0;
            for (int j = 0; j < i; j ++) {
                if (!isOverlapping(intervals[i], intervals[j])) {
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
        }
        return intervals.length - dp[intervals.length - 1];
    }


    /**
     * 我们的思路与上⽂中的动态规划⼀样，先求出最⼤不᯿叠⼦区间个数,再⽤「区间总数-最⼤不᯿叠⼦区
     * 间个数」即为最⼩要移除的᯿叠区间数。
     * ⽤贪⼼算法求最⼤不᯿⼤⼦区间个数步骤如下
     * 1. 选择终点最⼩的区间，设置为当前区间 cur 。
     * 2. 按区间终点从⼩到⼤寻找下⼀个与区间 cur 不᯿叠的区间，然后将此区间设置为当前区间 cur（注
     * 意此时最⼤不᯿叠⼦区间个数要加1），不断᯿复步骤 2， 直到遍历所有的区间。
     *       cur
     *      ————        从第一个区间开始遍历，寻找下一个不与cur区间不重叠的区间
     *     ——————
     *        ————
     *             ————
     *          ——————————
     */
    /**
     * 贪⼼算法求解
     * 时间复杂度是多少呢，只有⼀个循环，所以是 O(n), ⽐起动态规划的 O(n^2),确实快了⼀个数量级，简单
     * 分析下为啥贪⼼算法这么快，由以上代码可以看到，它只关⼼眼前的最优解（选择下⼀个与当前区间不
     * ᯿叠的区间再依次遍历，⽆选完之后再也⽆需求关⼼之前的区间了！）⽽动态规划呢，从它的 dp ⽅程
     * （dp[i] = max{dp[j]} + 1）可以看出，对于每个 i ,都要⾃底向上遍历⼀遍 0 到 i 的解以求出最⼤值，也
     * 就是说对于动态规划的⼦问题⽽⾔，由于它追求的是全局最优解，所以它有⼀个回溯（即⾃底向上求出
     * 所有⼦问题解的最优解）的过程，回溯的过程中就有⼀些᯿复的⼦问题计算，⽽贪⼼算法由于追求的是
     * 眼前的最优解，所以不会有这种回溯的求解，也就省去了⼤量的操作，所以如果可以⽤贪⼼算法求解，
     * 时间复杂度⽆疑是能上升⼀个量级的。
     */
    private static Integer removeSubDuplicateWithGreedy(Interval[] intervals) {
        // 将区间终点由⼩到⼤进⾏排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.end));
        int cur = 0; // 设置第⼀个为当前区间
        int count = 1; // 最⼤不重叠区间数,最⼩为1
        for (int i = 1; i < intervals.length; i++) {
            // 不重叠
            if (intervals[cur].end < intervals[i].start) {
                cur = i;
                count++;
            }
        }
        // 总区间个数减去最⼤不᯿叠区间数即最⼩被移除᯿叠区间
        return intervals.length - count;
    }


    // 区间类，包括起始值和终⽌值
    private static class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
