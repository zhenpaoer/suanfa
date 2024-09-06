package huawei.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定两个整数数组array1、array2，数组元素按升序排列。
 * 假设从array1、array2中分别取出一个元素可构成一对元素，
 * 现在需要取出k对元素， 并对取出的所有元素求和，计算和的最小值
 *
 * 注意：两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
 * 输入描述:
 * 输入两行数组array1、array2， 每行首个数字为数组大小size(0 < size <= 100);
 * 0 < array1[i] <= 1000
 * 0 < array2[i] <= 1000
 * 接下来一行为正整数k 0 < k <= array1.size() * array2.size()
 *
 * 输出描述: 满足要求的最小和
 * 示例1
 * 输入
 * 3 1 1 2
 * 3 1 2 3
 * 2
 * 输出
 * 4
 */
public class SumArray {

    public static void main(String[] args) {
        int[] a = {1, 1, 2};
        int[] b = {1, 1, 3};
        int k = 2;
        System.out.println(getMin(a, b, k));
    }


    private static int getMin(int[] a, int[] b, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                list.add(a[i] + b[j]);
            }
        }
        Collections.sort(list);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += list.get(i);
        }
        return sum;
    }
}
