/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 * 插入排序的思想和我们打扑克摸牌的时候一样，从牌堆里一张一张摸起来的牌都是乱序的，
 * 我们会把摸起来的牌插入到左手中合适的位置，让左手中的牌时刻保持一个有序的状态。
 *
 * 那如果我们不是从牌堆里摸牌，而是左手里面初始化就是一堆乱牌呢？ 一样的道理，我们把牌往手的右边挪一挪，
 * 把手的左边空出一点位置来，然后在乱牌中抽一张出来，插入到左边，再抽一张出来，插入到左边，再抽一张，插入到左边，
 * 每次插入都插入到左边合适的位置，时刻保持左边的牌是有序的，直到右边的牌抽完，则排序完毕。
 **/
public class InsertSort {
	public static void sort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int value = arr[i];
			int j = 0;//插入的位置
			for (j = i-1; j >= 0; j--) {
				if (arr[j] > value) {
					arr[j+1] = arr[j];//移动数据
				} else {
					break;
				}
			}
			arr[j+1] = value; //插入数据
		}
	}
	public static void main(String[] args) {
		int nums[] = {10,9,2,5,3,7,101,18};
		sort(nums);
		System.out.println(Arrays.toString(nums));
	}
}
