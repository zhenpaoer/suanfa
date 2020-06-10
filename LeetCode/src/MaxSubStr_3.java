/**
 * Created by zhangzhen on 2020/5/2
 */

import java.util.Arrays;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 输入: "abcabcbb"
 * 输出: 3解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: "bbbbb"
 * 输出: 1解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: "pwwkew"
 * 输出: 3解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 **/
public class MaxSubStr_3 {
	//自己实现，通过前4个用例，最后一个没通过
	public static int maxSubStr(String str){
		if(str == null) return 0;
		if ("".equals(str)) return 0;
		String[] split = str.split("");
		int[] dp = new int[str.length()+1];
		Arrays.fill(dp,1);
		int max = dp[0];
		for (int i = 1; i < str.length() ; i++) {
			for (int j = 0; j < i; j++) {
				if (split[j].equals(split[i])){
					dp[i] = i -j;
				}
			}
			max = Math.max(dp[i],max);
		}
		return max;
	}

	//大牛做法
	//start是截至i，以i为最后一个元素的最长不重复子串的起始位置，即索引范围是[start,i]的子串是以索引i为最后一个元素的最长子串。
	// 当索引从i-1增加到i时，原来的子串[start,i-1]新增了一个元素变为[start,i]，需要判断j是否与[start,i-1]中元素有重复。
	// 所以if s[j] in st:是判断s[j]相同元素上次出现的位置，和i孰大孰小。如果i大，说明[i,j-1]中没有与s[j]相同的元素，
	// 起始位置仍取i；如果i小，则在[i,j-1]中有了与s[j]相同的元素，所以起始位置变为st[s[j]]+1，即[st[sj]+1,j]。
	// 而省略掉的else部分，由于s[j]是第一次出现所以前面必然没有重复的，仍然用i作为起始位置即可。
	// 后面的ans=max(ans,j-i+1)中，括号中前者ans是前j-1个元素最长子串长度，
	// j-i+1是以s[j]结尾的最长子串长度，两者（最长子串要么不包括j，要么包括j）取最大即可更新ans，
	// 遍历所有i后得到整个输入的最长子串长度。
	public static int maxSubStr2(String s){
		int[] last = new int[128];
		//记录字符上一次出现的位置
		Arrays.fill(last,-1);
		int start = 0; //窗口开始位置
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i);
			start = Math.max(start,last[index]+1);
			res = Math.max(res,i-start+1);
			last[index] = i;
		}
		return res;
	}

	//类似双指针，一前一后逐个往后判断
	public static int maxSubStr3(String s){
		if (s.length() == 0) {
			return 0;
		}
		int i = 0, j = 0;
		int maxCount = 1;
		while (i < s.length() - 1 && j < s.length() - 1) {
			String subStr = s.substring(i, j + 1);
			if (!subStr.contains(String.valueOf(s.charAt(j + 1)))) {
				j++;
				int size = j - i + 1;
				maxCount = maxCount > size ? maxCount : size;
			} else {
				i++;
			}
		}
		return maxCount;
	}
	public static void main(String[] args) {
		String str1 = "bbbbb";
		String str2 = "abcabcbb";
		String str3 = "pwwkew";
		String str4 = "";
		String str5 = "au";
		System.out.println(maxSubStr2(str2));
	}
}
