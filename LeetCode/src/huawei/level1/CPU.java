package huawei.level1;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 题目描述：
 * 现有两组服务器QA和B，每组有多个算力不同的CPU，
 * 其中A[i]是A组第i个CPU的运算能力，B[i]是B组第i个CPU的运算能力。一组服务器的总算力是各CPU的算力之和。
 * 为了让两组服务器的算力相等，
 * 允许从每组各选出一个CPU进行一次交换，求两组服务器中，用于交换的CPU的算力，并且要求从A组服务器中选出的CPU，算力尽可能小。
 *
 * 输入描述：
 * 第一行输入为L1和L2，以空格分隔，L1表示A组服务器中的CPU数量，L2表示B组服务器中的CPU数量。
 * 第二行输入为A组服务器中各个CPU的算力值，以空格分隔。
 * 第三行输入为B组服务器中各个CPU的算力值，以空格分隔。
 * 1<=L1<=10000
 * 1<=L2<=10000
 * 1<=A[i]<=100000
 * 1<=B[i]<=10000
 * 输出描述：
 * 对于每组测试数据，输出两个整数，以空格分隔，依次表示A组选出的CPU算力、B组选出的CPU算力。
 * 要求从A组选出的CPU的算力尽可能小。
 * 补充说明：
 * 保证两组服务器的初始总算力不同。答案肯定存在。
 * 示例1
 * 输入：
 * 2 2
 * 1 1
 * 2 2
 * 输出：
 * 1 2
 * 说明：
 * 从A组中选出算力为1的CPU，与B组中算力为2的进行交换口
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class CPU {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取两个数组的长度
        int L1 = scanner.nextInt();
        int L2 = scanner.nextInt();
        scanner.nextLine(); // 读取行尾的换行符

        // 读取数组A
        int[] A = new int[L1];
        for (int i = 0; i < L1; i++) {
            A[i] = scanner.nextInt();
        }

        // 读取数组B
        int[] B = new int[L2];
        for (int i = 0; i < L2; i++) {
            B[i] = scanner.nextInt();
        }

        // 调用函数并打印结果
        int[] result = findCouPairs(A, B);
        if (result != null) {
            System.out.println(result[0] + " " + result[1]);
        } else {
            System.out.println("No such pair found.");
        }

        scanner.close();
    }

    public static int[] findCouPairs(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int num : A) sumA += num;
        for (int num : B) sumB += num;

        int delta = (sumA - sumB) / 2;

        // 使用HashSet提高查找效率
        HashSet<Integer> BSet = new HashSet<>();
        for (int num : B) {
            BSet.add(num);
        }

        // 将A排序后遍历
        Arrays.sort(A);
        for (int a : A) {
            int b = a - delta;
            if (BSet.contains(b)) {
                return new int[]{a, b};
            }
        }

        // 如果没有找到这样的对，则返回null
        return null;
    }
}