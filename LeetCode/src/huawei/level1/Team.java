package huawei.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用数组代表每个人的能力 一个比赛活动要求参赛团队的最低能力值为N
 * 每个团队可以由一人或者两人组成 且一个人只能参加一个团队 计算出最多可以派出多少只符合要求的队伍。
 *
 *
 * 输入描述：
 *
 * 第一行代表总人数，范围1-500000
 * 第二行数组代表每个人的能力
 * 数组大小，范围1-500000
 * 元素取值，范围1-500000
 * 第三行数值为团队要求的最低能力值，范围1-500000
 *
 *
 * 输出描述：
 *
 * 最多可以派出的团队数量
 *
 *
 * 示例
 *
 * 1.输入：
 *
 * 5
 * 3 1 5 7 9
 * 8
 * 输出：3
 * 说明：3、5组成一队，1、7一队，9自己一队，输出3
 *
 * 2.输入：
 *
 * 7
 * 3 1 5 7 9 2 6
 * 8
 * 输出：4
 * 说明：3、5组成一队，1、7一队，9自己一队，2、6一队，输出4
 *
 * 3.输入：
 *
 * 3
 * 1 1 9
 * 8
 * 输出：1
 * 说明：9自己一队，输出1
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {

    public static int f(int m, List<Integer> l, int n) {
        // 注意：m 在这个实现中未使用，假设题目中的m是多余的或不影响解题逻辑
        int res = 0;
        Collections.sort(l); // 对列表进行排序

        // 找到第一个大于或等于n的元素的索引
        int s = -1;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) >= n) {
                s = i;
                break;
            }
        }

        // 如果有满足条件的元素，则所有在其后面的元素（包括它本身）都可以直接算作一个团队
        if (s != -1) {
            res += l.size() - s;
        }

        // 接下来，使用双指针法检查小于n的元素能否两两配对满足条件
        int i = 0, j = s - 1;
        while (i < j) {
            if (l.get(i) + l.get(j) >= n) {
                res++;
                j--; // 移动右指针
            }
            i++; // 无论如何都移动左指针
        }

        return res;
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(3);
        l.add(5);
        l.add(4);
        l.add(1);

        int n = 6;
        int result = f(0, l, n); // 假设m不影响结果，这里传入0
        System.out.println("Number of teams: " + result);
    }
}