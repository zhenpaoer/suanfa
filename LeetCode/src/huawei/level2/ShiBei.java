package huawei.level2;

import java.io.IOException;
import java.util.*;

/**
 * 有一个考古学家发现一个石碑，但是很可惜，发现时其已经断成多段，原地发现n个断口整齐的石碑碎片。为了破解石碑内容，考古学家希望有程序能帮忙计算复原后的石碑文字组合数，你能帮忙吗？
 *
 * 输入描述：
 * 第一行输入 n,表示石碑碎片的个数;
 * 第二行依次输入石碑碎片上的文字内容s，共有n组;
 *
 * 输出描述：
 * 输出石碑文字的组合（按照升序排列），行末无多余空格。
 *
 * 备注:如果存在石碑碎片内容完全相同，则由于碎片间的顺序变换不影响复原后的碑文内容，即相同碎片间的位置变换不影响组合。
 *
 * 用例1:
 * 输入
 * 3
 * a b c
 *
 * 输出
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 *
 * 说明:当石碑片上内容为'a','b','c'时，则组合有abc，acb，bac，bca，cab，cba
 *
 * 用例2:
 * 输入
 * 3
 * a a b
 *
 * 输出
 * aab
 * aba
 * baa
 *
 * 说明:当石碑片上内容为'a','a','b'时，则组合有aab，aba，baa
 *
 * 用例3:
 * 输入
 * 3
 * a b ab
 *
 * 输出
 * aabb
 * abab
 * abba
 * baab
 * baba
 */
import java.util.*;
import java.io.*;

public class ShiBei {

    public static void allList(List<String> textList, String h, List<String> res) {
        if (textList.isEmpty()) {
            res.add(h);
            return;
        }

        for (int i = 0; i < textList.size(); i++) {
            String temp1 = textList.get(i);
            List<String> temp2 = new ArrayList<>(textList);
            temp2.remove(i);
            allList(temp2, h + temp1, res);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!scanner.hasNextInt()) {
                // 清除输入缓冲区直到遇到整数
                while (!scanner.hasNextInt()) {
                    String line = scanner.nextLine();
                    if (line.trim().isEmpty()) continue; // 如果只是空白行，则继续
                    System.out.println("Invalid input, please enter an integer:");
                }
            }

            int n = scanner.nextInt();
            scanner.nextLine(); // 消耗掉nextInt()后的换行符

            // 跳过空白行（如果有的话）
            if (scanner.hasNextLine() && scanner.nextLine().trim().isEmpty()) {
                scanner.nextLine(); // 再次读取下一行，即包含字符串的行
            }

            String s = scanner.nextLine();
            List<String> textList = Arrays.asList(s.split("\\s+")); // 使用正则表达式按一个或多个空格分割字符串

            List<String> tempResult = new ArrayList<>();
            String h = "";
            allList(textList, h, tempResult);

            Set<String> uniqueRes = new HashSet<>(tempResult);
            List<String> result = new ArrayList<>(uniqueRes);
            Collections.sort(result);

            for (String i : result) {
                System.out.println(i);
            }

            // 假设我们只需要处理一次输入
            break;

            // 注意：Java中没有system("pause")，这里不需要暂停
        }

        scanner.close();
    }
}