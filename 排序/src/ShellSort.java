/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
 *
 * 按增量序列个数 k，对序列进行 k 趟排序；
 *
 * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，
 * 分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 **/
public class ShellSort {
	public static void sort(int[] arr) {
		int length = arr.length;
		//区间
		int gap = 1;
		while (gap < length) {
			gap = gap * 3 + 1;
		}
		while (gap > 0) {
			for (int i = gap; i < length; i++) {
				int tmp = arr[i];
				int j = i - gap;
				//跨区间排序
				while (j >= 0 && arr[j] > tmp) {
					arr[j + gap] = arr[j];
					j -= gap;
				}
				arr[j + gap] = tmp;
			}
			gap = gap / 3;
		}
	}
	public static void main(String[] args) {
		int nums[] = {10,9,2,5,3,7,101,18};
		sort(nums);
		System.out.println(Arrays.toString(nums));
	}
}
