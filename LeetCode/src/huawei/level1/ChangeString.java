package huawei.level1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个字符串s，最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
 *
 * 变换规则：交换字符串中任意两个不同位置的字符。
 *
 * 输入描述
 * 一串小写字母组成的字符串s
 *
 * 输出描述
 * 按照要求进行变换得到的最小字符串。
 *
 * 备注
 * s是都是小写字符组成
 * 1 ≤ s.length ≤ 1000
 * 输入	bcdefa
 * 输出	acdefb
 */
public class ChangeString {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    private static String getResult(String s) {
        char[] minChars = s.toCharArray();
        Arrays.sort(minChars);
        String misS = new String(minChars);
        char[] sArr = s.toCharArray();
        if(s.equals(misS)){
            return misS;
        }
        for (int i = 0; i < s.length(); i++) {
            if(minChars[i]!=sArr[i]){
                char temp = sArr[i];
                sArr[i]=minChars[i];
                int index=s.lastIndexOf(minChars[i]);
                sArr[index]=temp;
                break;
            }
        }
        return new String(sArr);
    }
}
