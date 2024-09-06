package huawei.level2;

import java.util.Comparator;
import java.util.Vector;

/**
 * 在某个项目中有多个任务(用 tasks 数组表示)需要您进行处理，其中tasks[i] = [si, ei]，你可以在 si <= day <= ei 中的任意一天处理该任务。请返回你可以处理的最大任务数。
 *
 * 注：一天可以完成一个任务的处理。
 *
 * 输入描述：
 *
 * 第一行为任务数量 n，1 <= n <= 100000;
 * 后面 n 行表示各个任务的开始时间和终止时间，
 * 用 si 和 ei 表示，1 <= si <= ei <= 100000;
 *
 * 输出描述：
 *
 * 输出为一个整数，表示可以处理的最大任务数。
 *
 * 示例 1：
 *
 * 输入
 * 3
 * 1 1
 * 1 2
 * 1 3
 *
 * 输出
 * 3
 * 说明：第一天处理任务 1，第二天处理任务 2，第三天处理任务 3。
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public class TaskProccess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine(); // 消耗掉nextInt()后的换行符

        Vector<int[]> tasks = new Vector<>();
        for (int i = 0; i < n; i++) {
            int[] task = new int[2];
            task[0] = scanner.nextInt(); // 读取开始时间
            task[1] = scanner.nextInt(); // 读取结束时间
            tasks.add(task);
        }

        // 使用自定义比较器对任务进行排序，按照结束时间升序排序
        tasks.sort(Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int endTime = tasks.get(0)[1];

        for (int i = 1; i < tasks.size(); i++) {
            if (tasks.get(i)[0] >= endTime) {
                count++;
                endTime = tasks.get(i)[1];
            }
        }

        System.out.println(count);

        // 注意：Java中没有直接的system("pause")，这里只是为了演示而添加了一个简单的输入来暂停程序
        // 在实际应用中，你可能不需要这样做
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // 等待用户输入来暂停程序
    }
}