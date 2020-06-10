/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 * 花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max
 *
 * 开辟一块新的空间创建新的数组 B，长度为 ( max - min + 1)
 *
 * 数组 B 中 index 的元素记录的值是 A 中某元素出现的次数
 *
 * 最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数
 **/
public class CountingSort {
	public static void main(String[] args) {
		int nums[] = {10,9,2,5,3,7,101,18};
		CountingSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	public static void CountingSort(int[] arr){
		int max = arr[0];
		//找最大值
		for (int i=0;i<arr.length;i++){
			if (arr[i]>max) {
				max = arr[i];
			}
		}
		//初始化数组
		int[] countArr = new int[max+1];
		//计数
		for (int i = 0;i<arr.length;i++){
			countArr[arr[i]]++;
			arr[i] = 0;
		}
		//排序
		int index =0;
		for (int i = 0;i<countArr.length;i++){
			if (countArr[i]>0){
				arr[index++] = i;
			}
		}

	}
}
