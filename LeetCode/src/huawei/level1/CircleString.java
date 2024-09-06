package huawei.level1;

import java.util.Scanner;

/**
 * 给你一个字符串 s，字符串s首尾相连成一个环形 ，请你在环中找出 'o' 字符出现了偶数次最长子字符串的长度。
 */
public class CircleString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            int num = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'o') {
                    num++;
                }
            }
            if (num % 2 == 0) {
                System.out.println(s.length());
            } else {
                System.out.println(s.length() - 1);
            }

        }
        in.close();
    }
}
