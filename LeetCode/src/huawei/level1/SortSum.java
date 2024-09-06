package huawei.level1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，相同出现次数按照第一次出现顺序进行先后排序。
 *
 * 2 输入描述
 * 一个数组，之间用英文逗号(,)分割
 *
 * 3 输出描述
 * 数组，按英文逗号(,)分割
 *
 * 4 示例
 * 输入：1,3,3,3,5,5,5,4,2,2
 * 输出：3,5,2,1,4
 * 解释：
 * 3 和 5 的出现次数为3，且3在5之前出现，所以3在5前面。
 * 2的出现次数为2次。
 * 1和4的出现次数为1次，且1在4前面。
 * 即根据题意，输出3,5,2,1,4
 */
public class SortSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        // 不存在数字比较，没有必要转int
        String[] ints = line.split(",");
        String result = sortCount(ints);
        System.out.println(result);
    }

    public static String sortCount(String[] arr) {
        final int initialCapacity = arr.length / 2;
        // 设置一个Map，记录数字的出现次数
        Map<String, Integer> countMap = new HashMap<>(initialCapacity);
        // 设置另一个Map，记录数字的首次出现的位置
        Map<String, Integer> indexMap = new HashMap<>(initialCapacity);
        // 遍历，并记录次数和位置
        for (int i = 0; i < arr.length; i++) {
            String key = arr[i];
            int count = countMap.getOrDefault(key, 0) + 1;
            countMap.put(key, count);
            if (!indexMap.containsKey(key)) {
                indexMap.put(key, i);
            }
        }
        // 排序
        return countMap.entrySet().stream().sorted((v1, v2) -> {
                    if (!Objects.equals(v1.getValue(), v2.getValue())) {
                        // 从大到小输出
                        return v2.getValue() - v1.getValue();
                    }
                    // 从小到大输出
                    return indexMap.get(v1.getKey()) - indexMap.get(v2.getKey());
                }).map(it -> it.getKey())
                .collect(Collectors.joining(","));
    }
}

