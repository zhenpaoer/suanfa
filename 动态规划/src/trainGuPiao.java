/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.Arrays;

/**
 *给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 **/
public class trainGuPiao {

	//方式一
	public static int method(int[] arr){
		final int length = arr.length;
		int[] dp=new int[length+1];
		Arrays.fill(dp,0);
		int max = 0;
		for (int i = 1;i<length;i++){
			for (int j = 0; j<i;j++){
				if (arr[i]>arr[j]){
					 dp[i] = Math.max(arr[i] - arr[j],dp[i]);
				}
			}
			max = Math.max(dp[i],max);
		}
		return max;

	}

	//方式二
	public static int maxProfit(int[] prices) {
		int buy = -prices[0];
		int sell = 0;
		for (int i=1;i<prices.length;i++){
			buy = Math.max(buy,-prices[i]);
			sell = Math.max(sell,buy+prices[i]);
		}
		return sell;
	}

	/**给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

	 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

	 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
	 输入: [7,1,5,3,6,4]
	 输出: 7
	 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
	 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
	 *
	 **/
	public static int maxProfit1(int[] prices) {
		int buy = -prices[0];
		int sell = 0;
		for (int i=1;i<prices.length;i++){
			sell = Math.max(sell,buy+prices[i]);
			buy = Math.max(buy,sell-prices[i]);
		}
		return sell;
	}


	public static void main(String[] args) {
		int nums[] = {7,1,5,3,6,4};
		System.out.println(method(nums));
		System.out.println(maxProfit(nums));
		System.out.println(maxProfit1(nums));
	}
}
