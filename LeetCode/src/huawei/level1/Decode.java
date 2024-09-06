package huawei.level1;

import java.util.Scanner;

/**
 * 给定一段"密文"字符串s，其中字符都是经过"密码本"映射的，现需要将"密文"解密并且输出。
 *
 * 映射的规则 ："a-i"分别用"1-9"表示，"j-z" 分别用"10*-26*"表示
 *
 * 约束：映射始终唯一
 输入
 * 20*19*20*
 * 输出
 * tst
 */
public class Decode {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String cipherText = scanner.next();
            System.out.println(codeDecrypt(cipherText));
        }

        public static String codeDecrypt(String str) {
            StringBuilder ans = new StringBuilder();
            int index = 0;
            while (index < str.length()) {
                if (index + 2 < str.length() && str.charAt(index + 2) == '*') {
                    ans.append(Character.toString((char) (Integer.parseInt(str.substring(index, index + 2)) + 'a' - 1)));
                    index += 3;
                } else {
                    ans.append(Character.toString((char) (Integer.parseInt(str.substring(index, index + 1)) + 'a' - 1)));
                    index += 1;
                }
            }
            return ans.toString();
        }
    }