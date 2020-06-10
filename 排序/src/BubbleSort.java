/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 * @ClassName BubbleSort
 * @Description: TODO
 * @Author zhangzhen
 * @Date 2020/5/1 
 * @Version V1.0
 **/
public class BubbleSort {
	public static void BubbleSort(int arr[]){
		for( int i = 0 ; i < arr.length - 1 ; i++ ){
			for(int j = 0;j < arr.length - 1 - i ; j++){
				int temp = 0;
				if(arr[j] < arr[j + 1]){
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int nums[] = {10,9,2,5,3,7,101,18};
		BubbleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
}
