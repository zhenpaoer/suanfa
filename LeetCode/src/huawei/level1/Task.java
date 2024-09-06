package huawei.level1;

import java.util.PriorityQueue;

/**
 * 现有N个任务需要处理，同一时间只能处理一个任务，处理每个任务所需要的时间固定为1。
 *
 * 每个任务都有最晚处理时间限制和积分值，在最晚处理时间点之前处理完成任务才可获得对应的积分奖励。
 *
 * 可用于处理任务的时间有限，请问在有限的时间内，可获得的最多积分。
 *
 * 输入描述
 * 第一行为一个数 N，表示有 N 个任务
 *
 * 1 ≤ N ≤ 100
 *
 * 第二行为一个数 T，表示可用于处理任务的时间
 *
 * 1 ≤ T ≤ 100
 *
 * 接下来 N 行，每行两个空格分隔的整数（SLA 和 V），SLA 表示任务的最晚处理时间，V 表示任务对应的积分。
 *
 * 1 ≤ SLA ≤ 100
 *
 * 0 ≤ V ≤ 100000
 *
 * 输出描述
 * 可获得的最多积分
 *
 * 用例1
 * 输入
 *
 * 4
 * 3
 * 1 2
 * 1 3
 * 1 4
 * 1 5
 *
 * 输出
 *
 * 5
 *
 * 说明
 *
 * ❝
 * 虽然有3个单位的时间用于处理任务，可是所有任务在时刻1之后都无效。 所以在第1个时间单位内，选择处理有5个积分的任务。1-3时无任务处理。
 */
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入任务数量和时刻上限
        int n = scanner.nextInt();
        int T = scanner.nextInt();
        scanner.nextLine(); // 消耗掉换行符

        // 使用二维数组或ArrayList<ArrayList<Integer>>存储任务，但这里用PriorityQueue简化
        // 由于直接排序后使用，我们实际不存储完整任务列表，而是按需处理

        // 输入每个任务的开始时间和价值
        PriorityQueue<int[]> tasks = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 按开始时间排序
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            tasks.offer(new int[]{a, b});
        }

        // 创建一个小根堆，用于存储当前时刻的任务价值
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 遍历排序后的任务（这里实际上已经是按开始时间排序的）
        while (!tasks.isEmpty()) {
            int[] task = tasks.poll();
            int startTime = task[0];
            int value = task[1];

            // 如果当前任务在当前时刻没有过期（这里的逻辑需要调整，因为Java中时间不是以索引表示的）
            // 我们直接按时间顺序处理每个任务

            // 如果堆的大小小于上限 T，则直接添加任务价值
            if (minHeap.size() < T) {
                minHeap.offer(value);
            }
            // 如果堆已满，比较堆顶任务价值与当前任务价值
            // 如果当前任务价值大于堆顶任务价值，则替换堆顶任务价值
            else if (minHeap.peek() < value) {
                minHeap.poll();
                minHeap.offer(value);
            }
        }

        // 计算总任务价值
        long res = 0;
        while (!minHeap.isEmpty()) {
            res += minHeap.poll();
        }

        System.out.println(res);

        scanner.close();
    }
}