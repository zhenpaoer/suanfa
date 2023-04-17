package src.basic;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.objects.NativeJSON;
import netscape.javascript.JSObject;

import java.util.Arrays;

public class QuickSort {

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
//        for (int i = 0; i < 1; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            System.out.println("arr1="+ JSON.toJSONString(arr1));
//            int[] arr1 = new int[]{4,3,5,2,1,7,8,9};
            int[] arr2 = copyArray(arr1);
            Arrays.sort(arr2);
            System.out.println("arr2="+ JSON.toJSONString(arr2));
            proccess(arr1, 0, arr1.length - 1);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
//                break;
            }
//        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }


    public static void proccess(int[] arr,int L,int R){
        if (L >= R){
            return;
        }
        swap(arr,L +(int)Math.random()*(R-L+1),R);
        int[] sort = sort(arr, L, R);
        System.out.println("1" + JSON.toJSONString(arr));
        proccess(arr,L,sort[0]-1);
        System.out.println("2" +JSON.toJSONString(arr));
        proccess(arr,sort[1]+1,R);
        System.out.println("3" +JSON.toJSONString(arr));
    }

    public static int[] sort(int[] arr,int L ,int R){
        if (L > R){
            return new int[]{-1,-1};
        }
        if (L == R){
            return new int[]{L,R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more){
            if (arr[index] == arr[R]){
                index++;
            }else if (arr[index] < arr[R]){
                swap(arr,index++,++less);
            }else {
                swap(arr,index,--more);
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }

    public static void swap(int[] arr,int l,int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] =  temp;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
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
