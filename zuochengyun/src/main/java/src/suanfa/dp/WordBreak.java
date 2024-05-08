package src.suanfa.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139 单词拆分
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 */

public class WordBreak {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("aaaa");
        stringList.add("aaa");
        System.out.println(wordBreak("aaaaaaa", stringList));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        Set<String> set = new HashSet<>();
        int maxLength = 0;
        dp[0] = true;
        for (String s1 : wordDict) {
            set.add(s1);
            maxLength = Math.max(s1.length(),maxLength);
        }
        for(int i = 1;i<s.length()+1;i++){
            for (int j = i ; i- j <= maxLength && j >= 0; j-- ){
                if(set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
