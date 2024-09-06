package huawei.level2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 一个局域网内有很多台电脑，分别标注为0 - N-1的数字。相连接的电脑距离不一样，所以感染时间不一样，感染时间用t表示。其中网络内一个电脑被病毒感染，其感染网络内所有的电脑需要最少需要多长时间。如果最后有电脑不会感染，则返回-1给定一个数组times表示一个电脑把相邻电脑感染所用的时间。如图：path[i]= {i,j, t} 表示电脑i->j 电脑i上的病毒感染j，需要时间t。
 * 输入描述：
 * 4
 * 3
 * 2 1 1
 * 2 3 1
 * 3 4 1
 * 2
 * 输出描述：
 * 2
 */
import java.util.*;

public class vires {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<List<int[]>> list = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        int m = sc.nextInt();
        int a, b, t;
        while (m-- > 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            t = sc.nextInt();
            List<int[]> temp = list.get(a);
            if (temp == null) {
                temp = new ArrayList<>();
            }
            temp.add(new int[]{b, t});
            list.set(a, temp);
        }
        int start = sc.nextInt();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{start, 0});
        boolean[] flag = new boolean[n + 1];
        int[] time = new int[n + 1];
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int poll = queue.poll()[0];
            if (flag[poll]) {
                continue;
            }
            flag[poll] = true;
            for (int[] ints : list.get(poll)) {
                time[ints[0]] = time[poll] + ints[1];
                max = Math.max(time[ints[0]], max);
                queue.offer(new int[]{ints[0], time[ints[0]]});
            }
        }
        for (int i = 1; i < flag.length; i++) {
            if (!flag[i]) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(max);
    }
}



