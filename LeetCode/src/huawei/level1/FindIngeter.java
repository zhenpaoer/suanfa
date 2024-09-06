package huawei.level1;

import java.util.*;

/**
 * 查找众数及中位数
 * 1.众数是指一组数据中出现次数量多的那个数，众数可以是多个
 * 2.中位数是指把一组数据从小到大排列，最中间的那个数，如果这组数据的个数是奇数，那最中间那个就是中位数，如果这组数据的个数为偶数，那就把中间的两个数之和除以2，所得的结果就是中位数
 * 3.查找整型数组中元素的众数并组成一个新的数组，求新数组的中位数
 *
 * 输入描述:
 * 输入一个一维整型数组，数组大小取值范围 0<N<1000，数组中每个元素取值范围 0<E<1000
 * 输出描述:
 * 输出众数组成的新数组的中位数
 *
 * 示例1：
 * 输入
 * 10 11 21 19 21 17 21 16 21 18 15
 * 输出
 * 21
 * 示例2：
 * 输入
 * 2 1 5 4 3 3 9 2 7 4 6 2 15 4 2 4
 * 输出
 * 3
 * 示例3：
 * 输入
 * 5 1 5 3 5 2 5 5 7 6 7 3 7 11 7 55 7 9 98 9 17 9 15 9 9 1 39
 * 输出
 * 7
 */
import java.util.*;
public class FindIngeter {
    public static void main(String[] args) {
        // 输入
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] s = input.split(" ");
        int[] nums = new int[s.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
        scanner.close();
        // 获取众数数组和中位数
        Integer[] manyNums = getManyArr(nums);
        int medium = 0;
        int len = manyNums.length;
        if (len % 2 == 0) {
            medium = (manyNums[len / 2 - 1] + manyNums[len / 2]) / 2;
        } else {
            medium = manyNums[len / 2];
        }
        System.out.println(medium);
    }

    private static Integer[] getManyArr(int[] arr) {
        if (arr == null) {
            return new Integer[0];
        }
        // 将数组元素和出现的次数转换为key-value
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            if (countMap.containsKey(current)) {
                Integer count = countMap.get(current);
                countMap.put(current, ++count);
            } else {
                countMap.put(current, 1);
            }
        }
        // 获取出现最多的次数
        int countMax = 0;
        for (int value : countMap.values()) {
            if (value > countMax) {
                countMax = value;
            }
        }
        // 获取众数，并排序
        List<Integer> list = new ArrayList<>();
        for (int key : countMap.keySet()) {
            if (countMap.get(key) == countMax) {
                list.add(key);
            }
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Integer[] newArr = new Integer[list.size()];
        return list.toArray(newArr);
    }
}
