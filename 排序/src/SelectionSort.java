/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 * 选择排序的思路是这样的：首先，找到数组中最小的元素，拎出来，将它和数组的第一个元素交换位置，第二步，在剩下的元素中继续寻找最小的元素，拎出来，和数组的第二个元素交换位置，如此循环，直到整个数组排序完成。
 *
 * 至于选大还是选小，这个都无所谓，你也可以每次选择最大的拎出来排，也可以每次选择最小的拎出来的排，只要你的排序的手段是这种方式，都叫选择排序。
 **/
public class SelectionSort {
	public static void sort(int arr[]){
		for( int i = 0;i < arr.length ; i++ ){
			int min = i;//最小元素的下标
			for(int j = i + 1;j < arr.length ; j++ ){
				if(arr[j] < arr[min]){
					min = j;//找最小值
				}
			}
			int temp = arr[i];//交换位置
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
	public static void main(String[] args) {
		int nums[] = {10,9,2,5,3,7,101,18};
		sort(nums);
		System.out.println(Arrays.toString(nums));
	}
}
