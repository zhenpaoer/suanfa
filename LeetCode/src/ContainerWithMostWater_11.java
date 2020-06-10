/**
 * Created by zhangzhen on 2020/5/4
 */

/**
 *盛最多水的容器
 * @Date 2020/5/4
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 **/
public class ContainerWithMostWater_11 {
	//自己实现通过，采用双循环遍历
	public static int maxArea(int[] height) {
		if (height.length == 1 ) return height[0];
		if (height.length == 2 ) return Math.min(height[0],height[1]);
		int point = 2;
		int max = 0;
		int[] dp = new int[height.length];
		for (int i = 0; i < height.length; i++) {
			dp[point] = Math.max(dp[point],Math.min(height[i], height[point]) * (point - i));
			max = Math.max(dp[point],max);
			if (i==point ){
				i = -1;
				if (point == height.length-1){
					break;
				}
				point++;
			}

		}

		return max;
	}

	//采用双指针法，哪个指针的值小就移动哪个
	public static int maxAreaTwoPointer(int[] height){
		int right = height.length - 1;
		int left = 0;
		int maxArea = 0;
		while (left < right){
			//计算当前面积
			int area = Math.min(height[left], height[right]) * (right - left);
			maxArea = Math.max(maxArea,area);
			if (height[left] < height[right]){
				left++;
			}else {
				right--;
			}
		}
		return maxArea;
	}
	//按照官方题解写的代码。稍微优化了写法，看起来成语更加简便一些。
	public static int maxAreaTwoPointer2(int[] a){
		int max = 0;
		for(int i = 0, j = a.length - 1; i < j ; ){
			int minHeight = a[i] < a[j] ? a[i ++] : a[j --];
			max = Math.max(max, (j - i + 1) * minHeight);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] height = {1,8,6,2,5,4,8,3,7}; //49
		int[] height1 = {3,2,1,3};//9
		int[] height2 = {2,3,4,5,18,17,6};//17
//		System.out.println(maxArea(height));
//		System.out.println(maxArea(height1));
//		System.out.println(maxArea(height2));
		System.out.println(maxAreaTwoPointer(height2));
	}
}
