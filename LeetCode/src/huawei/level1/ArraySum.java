package huawei.level1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 给定一个数组，编写一个函数来计算它的最大N个数与最小N个数的和。你需要对数组进行去重。
 * 说明：*数组中数字范围[0, 1000],最大N个数与最小N个数不能有重叠，如有重叠，输入非法返回-1,输入非法返回-1
 *
 * 输入描述
 * ·第一行输入M，M标识数组大小·第二行输入M个数，标识数组内容
 * ·第三行输入N，N表达需要计算的最大、最小N个数
 *
 * 用例1
 * 5
 * 输入    95 88 83 64 100
 * 2
 * 输出    342
 * 说明    最大2个数[100,95],最小2个数[83,64],输出为342。
 * 用例2
 * 5
 * 输入    32342
 * 2
 * 输出    -1
 * 说明    最大2个数[4,3],最小2个数[3,2],有重叠输出为-1。
 */
public class ArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for(int i = 0;i< n;i++){
            arr[i] = scanner.nextInt();
        }
        int N = scanner.nextInt();
        System.out.println(sum(arr, N));
    }

    public static int sum(int[] arr,int n){
        HashSet<Integer> set = new HashSet<>();

        for (int val : arr) {
            if (val < 0 || val > 1000) return -1;
            set.add(val);
        }

        if (set.size() < n * 2) return -1;

        Integer[] distinct_arr = set.toArray(new Integer[0]);

        Arrays.sort(distinct_arr, (a, b) -> a - b);

        int l = 0;
        int r = distinct_arr.length - 1;
        int ans = 0;

        while (n > 0) {
            ans += distinct_arr[l] + distinct_arr[r];
            l++;
            r--;
            n--;
        }

        return ans;
    }
}
