package huawei.level1;

/**
 * 输入两个字符串 S 和 L，都只包含英文小写字母。S 长度<=100，L 长度<=500,000。
 * 判定 S 是否是 L 的有效子串。
 * 判定规则：
 * S 中的每个字符在 L 中都能找到（可以不连续），且 S 在Ｌ中字符的前后顺序与 S 中顺序要保持一致。
 * <p>
 * （例如，S=”ace”是 L=”abcde”的一个子序列且有效字符是 a、c、e，而”aec”不是有效子序列，且有效字符只有 a、e）
 * 输出S 串最后一个有效字符在 L 中的位置。（首位从 0 开始计算，无有效字符返回-1）
 */
public class EnableSubString {
    public static void main(String[] args) {
        System.out.println(getResult(new char[]{'a','c','e'},new char[]{'a','b','c','d','e'}));
    }

    public static int getResult(char[] S, char[] L) {
        int i = 0;
        int j = 0;
        while(i < S.length  && j < L.length){
            if(S[i] == L[j]){
                i++;
            }
            j++;
        }
        if( i == S.length ){
            return j-1;
        }else {
            return -1;
        }

    }
}
