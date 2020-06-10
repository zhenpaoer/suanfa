/**
 * Created by zhangzhen on 2020/5/14
 */

/**
 *  只出现一次的数字
 * @Description: TODO 给定一个非空整数数组，除了某个元素只出现一次以外，
 * 其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 输入: [2,2,1]
 * 输出: 1
 * 输入: [4,1,2,1,2]
 * 输出: 4
 **/
public class SingleNumber_136 {

	/**
	 * 交换律：a ^ b ^ c <=> a ^ c ^ b
	 * 任何数于0异或为任何数 0 ^ n => n
	 * 相同的数异或为0: n ^ n => 0
	 * var a = [2,3,2,4,4]
	 * 2 ^ 3 ^ 2 ^ 4 ^ 4等价于 2 ^ 2 ^ 4 ^ 4 ^ 3 => 0 ^ 0 ^3 => 3
	 **/
	public static int singleNumber(int[] nums) {
		if (nums == null) return 0;
		int n = 0;
		for (int num : nums) {
			n ^= num;
		}
		return n;
	}

	public static void main(String[] args) {
		int[] nums1 = {2,2,1};
		int[] nums2 = {4,1,2,1,2};
		System.out.println(singleNumber(nums2));

	}
}
