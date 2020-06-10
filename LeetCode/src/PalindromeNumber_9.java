/**
 * Created by zhangzhen on 2020/5/4
 */

/**
 * @Date 2020/5/4
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 **/
public class PalindromeNumber_9 {

	public static boolean isPalindrome(int x) {
		boolean flag = false;
		long n = 0;
		int temp = x;
		while (x >= 0 && temp != 0){
			n = n*10+temp%10;
			temp = temp/10;
		}
		n = (int)n==n?(int)n:0;
		if (x>=0 && x==n) flag = true;
		return flag;
	}

	public static boolean isPalindromedup(int x ){
		boolean flag = false;
		long res = Long.MAX_VALUE;
		int temp = x;
		while (temp != 0 && x > 0 ){
			res = res * 10 + temp  % 10; //结果先乘10，再去加个位数，求个位数就是将temp % 10就可以了
			temp =  temp / 10 ; //temp缩减一位
		}

		res = (int)res == res ? res:0;
		if (x >= 0 && res == x){
			flag = true;
		}
		return flag;
	}
	public static void main(String[] args) {
		int a1 = 121;
		int a2 = -121;
		int a3= 10;
		int a4= Integer.MAX_VALUE;
		int a5= Integer.MIN_VALUE;

		System.out.println(isPalindromedup(a3));
	}
}
