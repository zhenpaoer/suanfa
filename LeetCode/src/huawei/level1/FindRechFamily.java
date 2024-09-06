package huawei.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 在一棵树中，每个节点代表一个家庭成员，节点的数字表示其个人的财富值，一个节点及其直接相连的子节点被定义为一个小家庭。现给你一棵树，请计算出最富裕的小家庭的财富和。
 *
 * 输入描述：
 * 第一行为一个数N，表示成员总数，成员编号1~N,1<=N<=1000；
 * 第二行为N个空格分隔的数，表示编号1~N的成员的财富值，0<=财富值<=1000000；接下来N-1行，每行两个空格分隔的整数(N1 N2)，表示N1是N2的父节点。
 *
 * 输出描述：
 * 最富裕的小家庭的财富和
 *
 * 示例：
 * 输入
 * 4
 * 100 200 300 500
 * 1 2
 * 1 3
 * 2 4
 *
 * 输出
 * 700
 * 说明：
 * 成员1，2，3组成的小家庭财富值为600
 * 成员2，4组成的小家庭财富值为700
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindRechFamily {
    private static final int MAXN = 1005;
    private static int N;
    private static int maxWealth = 0;
    private static int[] wealth = new int[MAXN];
    private static List<Integer>[] children = new ArrayList[MAXN];

    static {
        // 初始化children数组，因为Java不会自动初始化对象数组的元素
        for (int i = 0; i < MAXN; i++) {
            children[i] = new ArrayList<>();
        }
    }

    public static void calculateFamilyWealth(int node, int parentWealth) {
        int familyWealth = parentWealth; // 起始节点自己的财富
        for (int child : children[node]) {
            calculateFamilyWealth(child, wealth[child]); // 递归计算子节点的小家庭财富
            familyWealth += wealth[child]; // 累加子节点的财富到当前小家庭
        }
        maxWealth = Math.max(maxWealth, familyWealth); // 更新最大小家庭财富
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入节点数
        N = scanner.nextInt();
        scanner.nextLine(); // 消耗掉换行符

        // 输入每个节点的财富
        for (int i = 1; i <= N; i++) {
            wealth[i] = scanner.nextInt();
        }

        // 输入家族关系
        for (int i = 1; i < N; i++) {
            int parent = scanner.nextInt();
            int child = scanner.nextInt();
            children[parent].add(child); // 构建孩子列表，注意这里只存储直接孩子，不考虑反向链接
        }

        // 从根节点（假设为1）开始计算
        calculateFamilyWealth(1, wealth[1]);

        // 输出最大小家庭财富
        System.out.println(maxWealth);

        // 注意：Java中不使用system("pause")，这里只是示例，实际开发中不需要
        // 如果你在IDE中运行，程序窗口通常会保持打开直到你手动关闭它
        // scanner.nextLine(); // 这只是为了在IDE中暂停程序，并不是必需的

        scanner.close();
    }
}