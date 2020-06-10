/**
 * Created by zhangzhen on 2020/5/5
 */

/**
 * @Date 2020/5/5
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 **/
public class LongestCommonPrefix_14 {
	public static void main(String[] args) {
		String[] s1 = {"flower","flow","floght"};
		String[] s2 = {"dog","racecar","car"};
		System.out.println(longestCommonPrefix(s1));
	}

	//第一个字符串为最大公共前缀，从第二个字符串开始判断是否存在该前缀，
	// 不存在时将字符串从后开始缩减直到存在，然后挨个遍历字符串数组。
	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0 || strs == null) return "";
		String res = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int i1 = strs[i].indexOf(res);
			while (strs[i].indexOf(res) != 0){
				res = res.substring(0, res.length() - 1);
			}
		}

		return res;
	}

	public static String longestCommonPrefixCopy(String[] strs){
		String a = strs[0];
		for (int i = 0; i < strs.length; i++) {
			while (strs[i].indexOf(a) != 0){
				a = a.substring(0,a.length() - 1);
			}
		}
		return a;
	}
}
