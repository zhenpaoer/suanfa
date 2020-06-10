/**
 * Created by zhangzhen on 2020/5/2
 */

import java.util.HashMap;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 输入: "cbbd"
 * 输出: "bb"
 **/
public class LongestSubstring_5 {

	//自己做的，可以解题，但是超出时间限制
	public static String longestPalindrome(String s) {
		if("".equals(s) || s == null) return "";
		String max = "";
		for (int i = 0; i < s.length(); i++) {
			for(int j = i; j < s.length() ;j++){
				String substring = s.substring(i, j + 1);
				if (substring.equals(new StringBuilder(substring).reverse().toString()) && (j-i+1) > max.length()){
					max = s.substring(i,j+1);
				}
			}
		}
		return  max;
	}

	public static String longestPalindrome2(String s) {
		String result = "";
		int[] limit = {0, 0};
		char[] ch = s.toCharArray();
		int i = 0;
		while (i < ch.length) {
			i = indexOf(ch, i, limit);
		}
		result = s.substring(limit[0], limit[1]);
		return result;
	}

	public static int indexOf(char[] ch, int low, int[] limit) {
		int high = low + 1;
		while (high < ch.length && ch[high] == ch[low]) {
			high++;
		}
		int result = high;
		while (low > 0 && high < ch.length && ch[low - 1] == ch[high]) {
			low--;
			high++;
		}

		if (high - low > limit[1] - limit[0]) {
			limit[0] = low;
			limit[1] = high;
		}
		return result;
	}

	public static void main(String[] args) {
		String babad = "babad";
		String cbbd = "cbbd";
		System.out.println(longestPalindrome(babad));
//		System.out.println(longestPalindrome2(cbbd));

	}
}
