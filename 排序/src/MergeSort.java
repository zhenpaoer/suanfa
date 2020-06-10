/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 * 现 merge 方法中只有一个 for 循环，直接就可以得出每次合并的时间复杂度为 O(n) ，
 * 而分解数组每次对半切割，属于对数时间 O(log n) ，合起来等于 O(log2n) ，也就是说，总的时间复杂度为 O(nlogn) 。
 *
 * 关于空间复杂度，其实大部分人写的归并都是在 merge 方法里面申请临时数组，用临时数组来辅助排序工作，
 * 空间复杂度为 O(n)，而我这里做的是原地归并，只在最开始申请了一个临时数组，所以空间复杂度为 O(1)。
 **/
public class MergeSort {

	public static void main(String[] args) {
		int nums[] = {10,9,2,5,3,7,101,18};
		sort(nums);
		System.out.println(Arrays.toString(nums));
	}

	public static void sort(int[] arr) {
		int[] tempArr = new int[arr.length];
		sort(arr, tempArr, 0, arr.length-1);
	}

	/**
	 * 归并排序
	 * @param arr 排序数组
	 * @param tempArr 临时存储数组
	 * @param startIndex 排序起始位置
	 * @param endIndex 排序终止位置
	 */
	private static void sort(int[] arr,int[] tempArr,int startIndex,int endIndex){
		if(endIndex <= startIndex){
			return;
		}
		//中部下标
		int middleIndex = startIndex + (endIndex - startIndex) / 2;

		//分解
		sort(arr,tempArr,startIndex,middleIndex);
		sort(arr,tempArr,middleIndex + 1,endIndex);

		//归并
		merge(arr,tempArr,startIndex,middleIndex,endIndex);
	}

	/**
	 * 归并
	 * @param arr 排序数组
	 * @param tempArr 临时存储数组
	 * @param startIndex 归并起始位置
	 * @param middleIndex 归并中间位置
	 * @param endIndex 归并终止位置
	 */
	private static void merge(int[] arr, int[] tempArr, int startIndex, int middleIndex, int endIndex) {
		//复制要合并的数据
		for (int s = startIndex; s <= endIndex; s++) {
			tempArr[s] = arr[s];
		}

		int left = startIndex;//左边首位下标
		int right = middleIndex + 1;//右边首位下标
		for (int k = startIndex; k <= endIndex; k++) {
			if(left > middleIndex){
				//如果左边的首位下标大于中部下标,证明左边的数据已经排完了。
				arr[k] = tempArr[right++];
			} else if (right > endIndex){
				//如果右边的首位下标大于了数组长度,证明右边的数据已经排完了。
				arr[k] = tempArr[left++];
			} else if (tempArr[right] < tempArr[left]){
				arr[k] = tempArr[right++];//将右边的首位排入,然后右边的下标指针+1。
			} else {
				arr[k] = tempArr[left++];//将左边的首位排入,然后左边的下标指针+1。
			}
		}
	}
}
