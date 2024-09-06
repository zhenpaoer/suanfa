package huawei.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 部门在进行需求开发时需要进行人力安排。
 *
 * 当前部门需要完成N个需求，
 * 需求用requirements[]表示，
 * requirements[i]表示第i个需求的工作量大小，单位：人月。
 * 这部分需求需要在M个月内完成开发，
 * 进行人力安排后每个月的人力是固定的。
 *
 * 目前要求每个月最多有2个需求开发，
 * 并且每个月需要完成的需求不能超过部门人力。
 *
 * 请帮部门评估在满足需求开发进度的情况下，
 * 每个月需要的最小人力是多少？
 * 输入描述
 *
 * 输入第一行为 M ，第二行为 requirements 。
 *
 * M 表示需要开发时间要求，requirements 表示每个需求工作量大小
 *
 * N` 为 `requirements` 长度，`1 ≤ N / 2 ≤ M ≤ N ≤ 10000`，`1 ≤ requirements[i]≤ 10^9
 *
 * 输出描述
 *
 * 对于每一组测试数据，输出部门需要人力需求，行末无多余的空格。
 *
 * 示例输入3
 *
 * 3 5 3 4
 *
 * 输出6
 */
import java.util.*;

public class RenLi {

    public static int countHumanSource(int M, int[] requirements) {
        // 将工作量数组排序
        Arrays.sort(requirements);

        // 设置二分搜索的左边界为列表中的最大值
        int req = requirements[requirements.length - 1];
        // 设置二分搜索的右边界为题目约定的人力上限
        int limitHuman = 2000000000;
        // 初始化答案变量为人力上限
        int humanNum = limitHuman;

        // 进行二分搜索
        while (req <= limitHuman) {
            int mid = (req + limitHuman) / 2;
            if (canCompleteInMonths(requirements, mid, M)) {
                humanNum = mid;
                limitHuman = mid - 1;
            } else {
                req = mid + 1;
            }
        }

        return humanNum;
    }

    // 辅助函数：检查是否可以在M个月内完成所有工作量
    private static boolean canCompleteInMonths(int[] requirements, int humanPower, int M) {
        int currentMonth = 1;
        int currentWork = 0;
        for (int requirement : requirements) {
            if (currentWork + requirement <= humanPower) {
                // 当前月可以完成这个工作量
                currentWork += requirement;
            } else {
                // 需要额外一个月
                currentMonth++;
                if (currentMonth > M) {
                    // 超出时间限制
                    return false;
                }
                currentWork = requirement;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取最大月数M
        System.out.print("Enter the maximum number of months (M): ");
        int M = scanner.nextInt();
        scanner.nextLine(); // 消耗掉换行符

        // 读取每个需求的工作量
        List<Integer> requirementList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            requirementList.add(scanner.nextInt());
            if (scanner.nextLine().isEmpty()) { // 检查是否遇到了空行
                break;
            }
        }

        // 转换为整数数组
        int[] requirements = new int[requirementList.size()];
        for (int i = 0; i < requirementList.size(); i++) {
            requirements[i] = requirementList.get(i);
        }

        // 排序
        Arrays.sort(requirements);

        // 计算最小人力需求
        int humanNums = countHumanSource(M, requirements);
        System.out.println("Minimum number of humans required: " + humanNums);

        // 注意：Java中没有system("pause")，通常不需要在控制台程序中暂停
        // 如果你在IDE中运行，它会自动等待你关闭控制台窗口
        // 如果你在命令行中运行，并且希望看到输出，你可能需要手动暂停（例如在Windows中使用pause命令）
    }
}