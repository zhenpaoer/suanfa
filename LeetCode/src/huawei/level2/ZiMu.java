package huawei.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开头和结尾都是元音字母（aeiouAEIOU）的字符串为元音字符串，其中混杂的非元音字母数量为瑕疵度。比如：
 *
 * “a”、“aa”是元音字符串，其瑕疵度都为0
 * “aiur”不是元音字符串（结尾不是元音字符）
 * “abira”是元音字符串，其瑕疵度为2
 * 给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0.
 *
 * 子串：字符串中任意个连续的字符组成的子序列称为该字符串的子串
 *
 * 解答要求
 *
 * 时间限制：1000ms，内存限制：256MB
 *
 * 输入
 * 首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0,65535]。
 *
 * 接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度（0,65535]。
 *
 * 输出
 * 输出为一个整数，代表满足条件的元音字符子串的长度。
 *
 * 样例
 * 输入
 *
 * 0  asdbuiodevauufgh
 *
 * 输出 3
 *
 * 提示 ：满足条件的最长元音字符子串有两个，分别为uio和auu，长度为3。
 *
 * 输入
 *
 * 2 aeueo
 *
 * 输出 0
 *
 * 提示 ： 没有满足条件的元音字符子串，输出0.
 *
 * 输入
 *
 * 1 aabeebuu
 *
 * 输出 5
 *
 * 提示 满足条件的最长元音字符子串有两个，分别为aabee和eebuu，长度为5
 */
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ZiMu {

    public static int getFlawStr(String str, int flaw) {
        int i = 0, j = 0, curFlaw = 0;
        int max = 0;
        List<String> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('e', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('u', 1);

        // 找到第一个元音字符
        while (i < str.length() && !map.containsKey(str.charAt(i))) {
            i++;
        }

        if (flaw == 0) {
            int k = i;
            while (k < str.length()) {
                if (!map.containsKey(str.charAt(k))) {
                    int len = k - i;
                    if (len > max) {
                        max = len;
                        // 在这个特殊场景下，我们不需要保存具体的字符串
                    }
                    k++;
                    i = k;
                } else {
                    k++;
                }
            }
            System.out.println("Max length with flaw = 0: " + max);
            return max;
        }

        while (j < str.length()) {
            if (!map.containsKey(str.charAt(j))) {
                curFlaw++;
                j++;
                continue;
            }

            // 当j位置的字符是元音，并且j+1位置也是元音或字符串末尾时
            while (j < str.length() - 1 && map.containsKey(str.charAt(j + 1))) {
                j++;
            }

            // 此时j指向非元音字符或字符串末尾
            if (curFlaw == flaw) {
                int len = j - i + 1;
                if (len > max) {
                    max = len;
                    // 如果需要保存字符串，可以添加如下代码
                    // res.add(str.substring(i, j + 1));
                }
            }

            // 尝试从i向右移动，直到遇到下一个非元音字符
            while (i <= j) {
                if (!map.containsKey(str.charAt(i))) {
                    curFlaw--;
                    i++;
                } else {
                    break;
                }
            }

            // 如果i和j相等，说明没有更多的元音字符可以探索，但在这个实现中，由于我们总是尝试移动j，这种情况不太可能直接发生
            // 除非字符串以非元音字符开头，且flaw大于0，这会在外层循环中被处理

            j++;
        }

        System.out.println("Max length with flaw = " + flaw + ": " + max);
        return max;
    }

    public static void main(String[] args) {
        String str = "asdbuiodevauufgh";
        int flaw = 1;
        getFlawStr(str, flaw);
        flaw = 0;
        getFlawStr(str, flaw);
        flaw = 3;
        getFlawStr(str, flaw);
    }
}