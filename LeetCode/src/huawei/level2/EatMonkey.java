package huawei.level2;

/**
 * 只贪吃的猴子，来到一个果园，发现许多串香蕉排成一行，每串香蕉上有若干根香蕉。每串香蕉的根数由数组numbers给出。
 *
 * 猴子获取香蕉，每次都只能从行的开头或者末尾获取，并且只能获取N次，求猴子最多能获取多少根香蕉。
 *
 * 输入描述
 * 第一行为数组numbers的长度
 *
 * 第二行为数组numbers的值每个数字通过空格分开
 *
 * 第三行输入为N，表示获取的次数
 * 输入描述
 * 第一行为数组numbers的长度
 * 第二行为数组numbers的值每个数字通过空格分开
 * 第三行输入为N，表示获取的次数
 * 输入
 * 7
 * 1 2 2 7 3 6 1
 * 3
 * 输出
 * 10
 * 输入
 * 3
 * 1 2 3
 * 3
 * 输出
 * 6
 */
import java.util.Scanner;

public class EatMonkey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numbers;
        System.out.print("请输入香蕉串的总数: ");
        numbers = scanner.nextInt();

        int[] ba = new int[numbers];
        for (int i = 0; i < numbers; ++i) {
            ba[i] = scanner.nextInt();
        }

        // 插入一个0到数组开头，模拟前缀和计算
        int[] baWithZero = new int[numbers + 1];
        for (int i = 0; i < numbers; ++i) {
            baWithZero[i + 1] = ba[i];
        }

        int[] total = new int[numbers + 1];
        total[0] = 0;
        for (int i = 1; i <= numbers; ++i) {
            total[i] = total[i - 1] + baWithZero[i]; // 计算前缀和
        }

        System.out.print("请输入从后端取香蕉的次数: ");
        int N = scanner.nextInt();
        int maxBa = 0;

        for (int i = 0; i < N; ++i) {
            int left = N - i; // 计算从后端取香蕉的次数
            int temp = total[i] + total[numbers] - total[numbers - left];
            maxBa = Math.max(maxBa, temp);
        }

        System.out.println("最大香蕉数: " + maxBa);

        // 防止控制台窗口立即关闭（如果需要）
        scanner.nextLine(); // 清除换行符
        System.out.print("按任意键继续...");
        scanner.nextLine();
    }
}