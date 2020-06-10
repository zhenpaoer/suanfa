/**
 * Created by zhangzhen on 2020/5/4
 */

/**
 * @Date 2020/5/4
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 输入: 123
 * 输出: 321
 * <p>
 * 输入: -123
 * 输出: -321
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 **/
public class ReverseInteger_7 {

	//自己写的，测试通过1000个测试用例，但是 1534236469、2147483647这样的出错了
	public static int reverse(int x) {
		boolean ismorezero = true;
		if (x < 0 ) {
			ismorezero = false;
			x = -x;
		}
		char[] array = String.valueOf(x).toCharArray();
		int end = array.length -1 ;
		for (int i = 0; i < array.length; i++) {
			if (i < end) {
				char temp = array[end];
				array[end] = array[i];
				array[i] = temp;
				end--;
			}
			else if (i == end) break;
			else if (i > end) break;
		}
		int res = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0){
				int pow = new Double(Math.pow(10, array.length - i - 1)).intValue();
				res = res + (Integer.parseInt(String.valueOf(array[i]))) * pow;
			}
			else if (array[i] == 0){

			}
		}
		if (!ismorezero){
			res = -res;
		}
		return res;

	}

	//自己模仿写
	public static int reverse_1(int x){
		long n = 0;
		while (x != 0){
			n = n*10+x%10;
			x = x/10;
		}
		return (int)n==n?(int)n:0;
	}

	//大牛解法2
	public static int reverse2(int x) {
		int ans = 0;
		while (x != 0) {
			if ((ans * 10) / 10 != ans) {
				ans = 0;
				break;
			}
			ans = ans * 10 + x % 10;
			x = x / 10;
		}
		return ans;
	}

	//大牛解法3
	public static int reverse3(int x) {
		long n = 0;
		while(x != 0) {
			n = n*10 + x%10;
			x = x/10;
		}
		return (int)n==n? (int)n:0;
	}

	public static int reverseDup(int x){
		long n = 0;
		while (x != 0 ){
			n = n * 10 + x % 10;
			x = x / 10;
		}
		return (int)n == n ? (int)n:0;
	}
	public static void main(String[] args) {
		int a1 = 123;
		int a2 = 321;
		int a3 = -321;
		int a4 = 1534236469;
//		System.out.println(reverse(a3));
//		System.out.println(reverse2(a3));
		System.out.println(reverse3(a4));
	}
}
