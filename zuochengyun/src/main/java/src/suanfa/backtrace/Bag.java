package src.suanfa.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有⼀个背包，背包总的承载᯿量是 Wkg。现在我们有 n 个物品，每个物品的᯿量不等，并且不可
 * 分割。我们现在期望选择⼏件物品，装载到背包中。在不超过背包所能装载᯿量的前提下，如何
 * 让背包中物品的总᯿量最⼤？ 假设这 n 个物品的质量分别 3kg, 4kg, 6kg, 8kg，背包总的承载᯿量
 * 是 10kg。
 */
public class Bag {
    private static Integer result = 0;
    private static final Integer max = 10;
    private static final List<Integer> nums = Arrays.asList(3,4,6,8);

    public static void main(String[] args) {
        proccess(new ArrayList<>(),nums);
        System.out.println(result.toString());
    }

    private static void proccess(List<Integer> selectedNums,List<Integer> nums){
        int sum = selectedNums.stream().mapToInt(Integer::intValue).sum();
        if (sum == max){
            result = Math.max(sum,result);
            return;
        }else {
            if (sum > max){
                selectedNums.remove(selectedNums.size() -1 );
                sum = selectedNums.stream().mapToInt(Integer::intValue).sum();
                result = Math.max(sum,result);
                return;
            }else {
                result = Math.max(sum,result);
            }
        }

        for (Integer num : nums) {
            if (selectedNums.contains(num)){
                continue;
            }
            selectedNums.add(num);
            proccess(selectedNums,nums);
            selectedNums.remove(num);
        }
    }
}
