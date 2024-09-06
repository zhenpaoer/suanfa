package huawei.level2;

import java.util.ArrayList;
import java.util.List;

/**
 * 某部门计划通过结队编程来进行项目开发，已知该部门有 N 名员工，每个员工有独一无二的职级，每三个员工形成一个小组进行结队编程，结队分组规则如下：
 *
 * 从部门中选出序号分别为 i、j、k 的3名员工，他们的职级分别为 level[i]，level[j]，level[k]，结队小组满足 level[i] < level[j] < level[k] 或者 level[i] > level[j] > level[k]，其中 0 ≤ i < j < k < n。
 *
 * 请你按上述条件计算可能组合的小组数量。同一员工可以参加多个小组。
 *
 * 输入描述
 * 第一行输入：员工总数 n
 *
 * 第二行输入：按序号依次排列的员工的职级 level，中间用空格隔开
 *
 * 备注：
 *
 * 1 <= n <= 6000
 *
 * 1 <= level[i] <= 10^5
 *
 * 输出描述
 * 可能结队的小组数量
 *
 * 示例1
 * 输入：
 * 4
 * 1 2 3 4
 *
 * 输出：
 * 4
 *
 * 说明：
 * 可能结队成的组合(1,2,3)、(1,2,4)、(1,3,4)、(2,3,4)
 * 示例2
 * 输入：
 * 3
 * 5 4 7
 *
 * 输出：
 * 0
 *
 * 说明：
 * 根据结队条件，我们无法为该部门组建小组
 *
 */
import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class Deparent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        List<Integer> level = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            level.add(scanner.nextInt());
        }

        int cnt = 0;

        for (int j = 1; j < num - 1; j++) {
            int inCnt = 0;
            int deCnt = 0;

            for (int i = 0; i < j; i++) {
                if (level.get(i) < level.get(j)) {
                    inCnt++;
                } else if (level.get(i) > level.get(j)) {
                    deCnt++;
                }
            }

            for (int k = j + 1; k < num; k++) {
                if (level.get(j) < level.get(k)) {
                    cnt += inCnt;
                } else if (level.get(j) > level.get(k)) {
                    cnt += deCnt;
                }
            }
        }

        System.out.println(cnt);

        scanner.close();
    }
}