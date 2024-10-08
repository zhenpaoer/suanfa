package huawei.level1;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
 *
 * 数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
 *
 * 给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，
 *
 * 即平均失败率小于等于minAverageLost，找出数组中最长时间段，如果未找到则直接返回NULL。
 *
 * 输入描述:
 *
 * 输入有两行内容，
 *
 * 第一行为{minAverageLost}，
 *
 * 第二行为{数组}，数组元素通过空格(" ")分隔，minAverageLost及数组中元素取值范围为0~100的整数，
 *
 * 数组元素的个数不会超过100个。
 *
 * 输出描述:
 *
 * 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，
 * 格式{beginIndex}-{endIndx}(下标从0开始)，
 * 如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(" ")拼接，多个下标对按下标从小到大排序。
 * 输入
 * 1
 * 0 1 2 3 4
 * 输出
 * 0-2
 * 输入
 * 2
 * 0 0 100 2 2 99 0 2
 * 输出
 * 0-1 3-4 6-7
 */

import java.util.ArrayList;
import java.util.List;

public class ServiceMachine {
    public static void main(String[] args) {
        int m = 2;
        String s = "0 0 100 2 2 99 0 2";
        String[] arr = s.split(" ");
        List<Integer> numbers = new ArrayList<>();
        for (String num : arr) {
            numbers.add(Integer.parseInt(num));
        }

        List<String> res = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            int t = -1;

            for (int j = i + 1; j < numbers.size(); j++) {
                List<Integer> tmp = numbers.subList(i, j + 1);
                if (calculateAverage(tmp) <= m) {
                    if (t < j) {
                        t = j;
                    }
                }
            }

            if (t != -1) {
                res.add(i + "-" + t);
            }
        }

        for (String range : res) {
            System.out.print(range + " ");
        }
    }

    private static double calculateAverage(List<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.size();
    }
}