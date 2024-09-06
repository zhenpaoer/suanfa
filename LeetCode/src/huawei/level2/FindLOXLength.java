package huawei.level2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给你一个字符串 s，字符串 s 首尾相连成一个环形，请你在环中找出 ‘l’、‘o’、‘x’ 字符都恰好出现了偶数次最长子字符串的长度。
 *
 * 输入描述
 * 输入是一串小写的字母组成的字符串
 *
 * 输出描述
 * 输出是一个整数
 *
 * 1 ≤ s.length ≤ 5 * 10^5
 *
 * s只包含小写英文字母
 *
 * 示例1
 * 输入
 * alolobo
 * 输出
 * 6
 * 输入
 * looxdolx
 * 输出
 * 7
 */
public class FindLOXLength {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String ss = s + s;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            String substring = ss.substring(i, i + s.length());
            max = Math.max(findLOXFromString(substring), max);
        }
        System.out.println(max);

        in.close();

    }
    public static int findLOXFromString(String s) {
        int res = 0;
        // key转态：字符出现位置二进制000  每一位代表字符出现的LOX是奇数还是偶数。奇数为1 偶数为0
        // value 第一次出现的位置
        Map<Byte, Integer> statusPos = new HashMap<>();
        statusPos.put((byte) 0, -1);
        Byte status = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'l':
                    status = (byte) (status ^ Byte.valueOf("001", 2));
                    break;
                case 'o':
                    status = (byte) (status ^ Byte.valueOf("010", 2));
                    break;
                case 'x':
                    status = (byte) (status ^ Byte.valueOf("100", 2));
                    break;
            }

            Integer sp = statusPos.getOrDefault(status, null);
            if (sp == null) {
                statusPos.put(status, i);
            } else {
                res = Math.max(res, i - sp);
            }
        }
        return res;

    }


}
