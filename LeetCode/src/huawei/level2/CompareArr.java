package huawei.level2;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个只包含数字的数组A,B，调整数组A里面数字的顺序，使得尽可能多的 A[i] > B[i]。数组 A和B中的数字各不相同。输出所有可以达到最优结果Q的A数组的数量
 *         输入描述:
 *         输入的第一行是数组a中的数字，其中只包含数字，每两个数字之间相隔一个空格，a 数组大小不超过 10输入的第二行是数组b中的数字，其中只包含数字，每两个数字之间相隔一个空格，b数组大小不超过 10输出描述
 *         输出所有可以达到最优结果的 A数组数量
 *         示例1:
 *         *输入:
 *         13 8 20
 *         9 12 7
 *         输出:
 *         说明:
 *         最优结果有两个，[13,20,8]和[20,13 8]，故输出 2
 *         示例2:
 *         输入:
 *         11 12 20
 *         10 13 7
 *         输出:
 *         说明:有两个A数组的排列可以达到最优结果[12,20,11]和[11,20,12]，故输出 2。
 */
import java.util.*;

public class CompareArr {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] aString = sc.nextLine().split(" ");
        String[] bString = sc.nextLine().split(" ");

        int[] aNums = new int[aString.length];
        int[] bNums = new int[bString.length];
        for(int i=0; i< aString.length;i++) {
            aNums[i] = Integer.parseInt(aString[i]);
        }
        for(int i=0; i< bString.length;i++) {
            bNums[i] = Integer.parseInt(bString[i]);
        }
        //获取aNums的所有全排列数组
        List<int[]> allNums = new ArrayList<>();
        permute(aNums, 0, allNums);

        //计算最优解情况下最多有几个元素大于b数组中的对应元素
        int counterMax = 0;
        for (int[] allNum : allNums) {
            int counter = 0;
            for (int i = 0; i < allNum.length; i++) {
                if (allNum[i] > bNums[i]){
                    counter++;
                }
            }
            counterMax = Math.max(counterMax, counter);
        }

        //计算最优情况下有多少数组
        int result = 0;
        for (int[] allNum : allNums) {
            int counter = 0;
            for (int i = 0; i < allNum.length; i++) {
                if (allNum[i] > bNums[i]){
                    counter++;
                }
            }
            if (counter == counterMax){
                result++;
            }
        }
        System.out.println(result);

    }

    //全排序算法
    private static void permute(int[] nums, int start, List<int[]> result) {
        if (start == nums.length - 1) {
            // Found a permutation
            int[] permutation = nums.clone();
            result.add(permutation);
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                permute(nums, start + 1, result);
                swap(nums, start, i); // backtrack
            }
        }
    }

    //集合元素换位算法
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}