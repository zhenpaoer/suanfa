package huawei.level2;

import java.util.LinkedList;

/**
 * 有一个文件，包含以一定规则写作的文本，请统计文件中包含的文本数量。
 *
 * 规则如下：
 *
 * 1. 文本以”;”分隔，最后一条可以没有”;”，但空文本不能算语句，比如”COMMAND A; ;”只能算一条语句。注意，无字符/空白字符/制表符都算作”空”文本；
 *
 * 2. 文本可以跨行，比如下面，是一条文本，而不是三条；
 *
 * COMMAND A
 *
 * AND
 *
 * COMMAND B;
 *
 * 3. 文本支持字符串，字符串为成对的单引号(')或者成对的双引号(")，字符串可能出现用转义字符(\)处理的单双引号("your input is\"")和转义字符本身，比如
 *
 * COMMAND A "Say \"hello\"";
 *
 * 4. 支持注释，可以出现在字符串之外的任意位置注释以”-“开头，到换行结束，比如：
 *
 * COMMAND A; -this is comment
 *
 * COMMAND -comment
 *
 * A AND COMMAND B;
 *
 * 注意字符串内的”-“，不是注释。
 *
 * 输入描述
 * 文本文件
 *
 * 输出描述
 * 包含的文本数量
 */
import java.util.LinkedList;
import java.util.Scanner;

public class Command {
    // 输入获取
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedList<String> lines = new LinkedList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if ("".equals(line)) {
                System.out.println(getResult(lines));
                sc.close();
                break;
            } else {
                lines.add(line);
            }
        }
    }

    // 算法入口
    public static int getResult(LinkedList<String> lines) {
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            line =
                    line.replaceAll("\\\\[\"']", "") // 替换\"和\'为空串
                            .replaceAll("\".*?\"", "a") // 将成对双引号及其中内容替换为空串
                            .replaceAll("'.*?'", "a") // 将成对单引号及其中内容替换为空串
                            .replaceAll("-.+", "") // 将-往后的注释替换为空串
                            .replaceAll("\\s+", "") // 将空白字符替换为空串
                            .replaceAll(";+", ";"); // 将连续分号替换为单个分号

            sb.append(line);
        }

        // 题目描述说：文本以”;”分隔，最后一条可以没有”;”
        // 为了避免复杂处理，这里无论最后一条文本有没有分号，都加一个
        sb.append(";");

        // 下面处理主要是为了处理跨行文本
        /***
         * 比如
         * aaaa;
         * ;aaaa
         *
         * 比如
         * ;aaaa
         * ;aaaa
         */
        String s = sb.toString().replaceAll(";+", ";").replaceAll("^;", "");

        // 记录文本条数
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ';') count++;
        }

        return count;
    }
}