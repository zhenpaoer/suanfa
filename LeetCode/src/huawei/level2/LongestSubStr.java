package huawei.level2;

/**
 * 提取字符串中的最长合法简单数学表达式，字符串长度最长的，并计算表达式的值。如果没有，则返回 0 。
 *
 * 简单数学表达式只能包含以下内容：
 * 0-9数字，符号+-*
 *
 * 说明：
 * 1、所有数字，计算结果都不超过long
 * 2、如果有多个长度一样的，请返回第一个表达式的结果
 * 3、数学表达式，必须是最长的，合法的
 * 4、操作符不能连续出现，如 +--+1 是不合法的
 *
 * 输入描述：
 * 字符串
 *
 * 输出描述：
 * 表达式值
 *
 * 示例1：
 * 输入
 *
 * 1-2abcd
 *
 *
 * 输出
 *
 * -1
 * 说明：最长合法简单数学表达式是"1-2"，结果是 -1
 */
import java.util.Scanner;

public class LongestSubStr {

    public static long longCalculateExpression(String expr) {
        long result = 0, num = 0, result_1 = 1, num_1 = 1;
        char op = '+'; // 初始化操作符为加

        for (char ch : expr.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
                num_1 = num_1 * 10 + (ch - '0');
            } else if (ch == '+' || ch == '-') {
                // 处理前一个操作符
                if (op == '+') result += num;
                else if (op == '-') result -= num;
                    // 注意：Java中不直接支持*运算符在字符串解析时的累计，这里仅保持C++逻辑结构
                else if (op == '*') result_1 *= num_1;

                num = 0;
                num_1 = 1;
                op = ch;
            } else {
                // 非法字符
                return 0;
            }
        }

        // 处理最后一个数字
        if (op == '+') result += num;
        else if (op == '-') result -= num;
        else if (op == '*') result_1 *= num_1;

        // 如果最后操作是乘法，则返回乘法结果
        if (op == '*') return result_1;

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine(); // 读取输入的字符串
        int maxLen = 0;
        long ans = 0;

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String subStr = str.substring(i, j);
                if (subStr.matches("[0-9+-]*")) { // 确保子串只包含允许的字符
                    long tempResult = longCalculateExpression(subStr);
                    if (tempResult != 0 && subStr.length() > maxLen) {
                        maxLen = subStr.length();
                        ans = tempResult;
                    }
                }
            }
        }

        System.out.println(ans);
        // 注意：Java中通常不使用system("pause")，因为这不是跨平台的
        scanner.close();
    }
}
