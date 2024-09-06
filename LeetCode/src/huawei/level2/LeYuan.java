package huawei.level2;

import java.util.Arrays;

/**
 * Wonderland是小王居住地一家很受欢迎的游乐园。Wonderland目前有4种售票方式，分别为一日票(1天)、三日票(3天)、周票(7天)和月票(30天) 每种售票方式的价格将由一个数组给出，每种票据在票面时限内可以无限制的进行游玩。例如，小王在第10日买了一张三日票，小王可以在第10日第11日和第12日进行无限制的游玩。 小王计划在接下来一年内多次游玩该游乐园。小王计划的游玩日期将由一个数组给出。现在，请您根据给出的售票价格数组和小王计划游玩日期数组，返回完成游玩计划所需要的最低消费。
 *
 * 输入描述:
 *
 * 输入为2个数组 售票价格数组为costs，costs.length=4,默认顺序为一日票、三日票、周票和月票。 小王计划游玩日期数组为days，1<=days.length<=365,1<=days[i]<=365,默认顺序为升序。
 *
 * 输出描述:
 *
 * 完成游玩计划的最低消费 备注: 样例说明: 根据售票价格数组和游玩日期数组给出的信息，发现每次去玩的时候买一张一日票是最省钱的，所以小王会买8张一日票，每张5元，最低花费是40元。
 *
 * 示例1
 *
 * 输入
 *
 * 5 14 30 100
 *
 * 13 15 20 21 200 202 230
 *
 * 输出
 *
 * 40
 */
import java.util.Arrays;
        import java.util.Scanner;

public class LeYuan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 初始化售票价格数组和游玩日期数组
        int[] costs = new int[4];
        int[] days;

        // 输入售票价格数组
        System.out.println("请输入售票价格数组（依次为一日票、三日票、周票和月票，用空格分隔）：");
        String[] costsInput = scanner.nextLine().split(" ");
        for (int i = 0; i < 4; i++) {
            costs[i] = Integer.parseInt(costsInput[i]);
        }

        // 输入游玩日期数组
        System.out.println("请输入小王计划游玩日期数组（升序排列，用空格分隔）：");
        String daysInput = scanner.nextLine();
        String[] daysStrArr = daysInput.split(" ");
        days = Arrays.stream(daysStrArr).mapToInt(Integer::parseInt).toArray();

        // 计算完成游玩计划的最低消费
        int minCost = minCostTickets(costs, days);
        System.out.println("完成游玩计划的最低消费为：" + minCost);

        scanner.close();
    }

    // 计算完成游玩计划的最低消费方法
    public static int minCostTickets(int[] costs, int[] days) {
        int n = days.length;
        int[] dp = new int[366]; // 动态规划数组
        boolean[] travel = new boolean[366]; // 标记游玩日期

        // 标记游玩日期
        for (int day : days) {
            travel[day] = true;
        }

        // 动态规划计算最低消费
        for (int i = 1; i <= 365; i++) {
            if (!travel[i]) {
                dp[i] = dp[i - 1];
                continue;
            }

            dp[i] = dp[i - 1] + costs[0]; // 单日票消费
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 1)] + costs[0]); // 比较一日票
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 3)] + costs[1]); // 比较三日票
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[2]); // 比较周票
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[3]); // 比较月票
        }

        return dp[365]; // 返回完成游玩计划的最低消费
    }
}