package src.basic;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 快排的变种
 *  题目要求：快速选择第k个大的元素
 *  应于快排一起背下来
 */
public class QuickSelect {
    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < 100; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr1 = new int[]{3,5,4,0,4,6,7,2};
            System.out.println("arr1="+ JSON.toJSONString(arr1));
//            int[] arr1 = new int[]{4,3,5,2,1,7,8,9};
            int[] arr2 = copyArray(arr1);
            Arrays.sort(arr2);
            System.out.println("arr2="+JSON.toJSONString(arr2));
            int k1 = arr2[arr1.length - 2];
            int k2 = findKthLargest(arr1, 2);
            System.out.println("k1="+k1+",k2="+k2);
            if (k1 != k2) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        k = n -k;
        int l = 0, h = n-1;
        while(l < h){
            int j = partition(nums,l,h);
            if(j == k){
                return nums[j];
            }else if(j > k){
                h = j-1;
            }
            else{
                l = j + 1;
            }
        }
        return nums[l];

    }
    private static int partition(int[]nums,int l,int h){
        int i = l, j = h + 1;
        int povit = nums[l];
        while(true){
            while(nums[++i] < povit && i != h);
            while(nums[--j] > povit && j != l);
            if(i >= j){
                break;
            }
            swap(nums,i,j);
        }
        swap(nums,l,j);
        return j;
    }
    private static void swap(int[]nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
}
