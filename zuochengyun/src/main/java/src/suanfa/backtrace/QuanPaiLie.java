package src.suanfa.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定数字 1，2，3，求出 3 位不重复数字的全排列
 */
public class QuanPaiLie {
    private static final List<Integer> nums = Arrays.asList(1,2,3);
    private static final List<String> result = new ArrayList<>();
    public static void main(String[] args) {
        proccess(new ArrayList<>(),nums);
        System.out.println(result.toString());
        result.clear();
        proccess1(new ArrayList<>(),nums);
        System.out.println(result.toString());
        permute(new int[]{1,2,3});
        System.out.println(ans.toString());
    }

    private static final List<List<Integer>> ans = new ArrayList<>();
    private static final  List<Integer> selected = new ArrayList<>();
    public static List<List<Integer>> permute(int[] nums) {

        proccess(nums,selected);
        return ans;
    }

    private static void proccess(int[] nums,List<Integer> selected){
        if(selected.size() == nums.length){
            ans.add(new ArrayList<>(selected));
            return;
        }
        for(Integer num:nums){
            if(selected.contains(num)){
                continue;
            }
            selected.add(num);
            proccess(nums,selected);
            selected.remove(num);
        }

    }
    public static void proccess1(List<Integer> selected,List<Integer> all){
        if (selected.size() == all.size()){
            result.add(selected.toString());
            return;
        }
        for (Integer integer : all) {
            if (selected.contains(integer)){
                continue;
            }
            selected.add(integer);
            proccess1(selected,all);
            selected.remove(integer);
        }
    }













    public static void proccess(List<Integer> selectedNums, List<Integer> selectableNums){
        //如果每个子序列数量够了，则把子序列放入到结果集中
        if (selectedNums.size() == nums.size()){
            result.add(selectedNums.toString());
            return;
        }
        //循环遍历可选值
        for (Integer select:selectableNums){
            //如果子序列里已经有这个值 则不再添加，是过滤重复数值
            if (selectedNums.contains(select)){
                continue;
            }
            //在子序列中加入可选值
            selectedNums.add(select);
            //加入完之后递归调用，放入下一个可选值，当都放了之后，此时的子序列就没有用了
            proccess(selectedNums,selectableNums);
            //子序列删除掉可选值
            selectedNums.remove(select);
        }

    }

}
