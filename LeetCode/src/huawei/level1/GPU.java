package huawei.level1;

/**
 * 为了充分发挥GPU算力，需要尽可能多的将任务交给GPU执行，现在有一个任务数组，数组元素表示在这1秒内新增的任务个数且每秒都有新增任务。
 * 假设GPU最多一次执行n个任务，一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成。
 * 输入描述:
 * 第一个参数为GPU一次最多执行的任务个数，取值范围[1，10000]
 * 第二个参数为任务数组长度，取值范围[1，10000]
 * 第三个参数为任务数组，数字范围[1,10000]
 * 输出描述:
 * 执行完所有任务最少需要多少秒。
 * 示例1
 * 输入输出示例仅供调试，后台判题数据─般不包含示例
 * 输入
 * 3
 * 5
 * 1 2 3 4 5
 * 输出
 * 6
 * 说明
 * —次最多执行3个任务，最少耗时6s
 * 示例2
 * 输入输出示例仅供调试，后台判题数据─般不包含示例
 * 输入
 * 4
 * 5
 * 5 4 1 1 1
 * 输出
 * 5
 */


import java.util.Scanner;

public class GPU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxTask = sc.nextInt();
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(executeTime(nums, maxTask));
    }

    private static int executeTime(int[] nums, int maxTask) {
        int res = 0;
        int curNum, leaveNum = 0;
        while (res < nums.length || leaveNum != 0) {
            curNum = leaveNum;
            if (res < nums.length) curNum += nums[res];
            leaveNum = curNum > maxTask ? curNum - maxTask : 0;
            res++;
        }
        return res;
    }
}


