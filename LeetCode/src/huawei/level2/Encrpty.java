package huawei.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 有一种特殊的加密算法，明文为一段数字串，经过密码本查找转换，生成另一段密文数字串。
 *
 * 规则如下：
 * 1. 明文为一段数字串由 0~9 组成
 * 2. 密码本为数字 0~9 组成的二维数组
 * 3.需要按明文数字串串的数字顺序在密码本里找到同样的数字串，密码本里的数字串是由相邻的单元格数字组成，上下和左右是相邻的，注意：对角线不相邻，同一个单元格的数字不能重复使用。
 * 4. 每一位明文对应密文即为密码本中找到的单元格所在的行和列序号（序号从0开始）组成的两个数宇。 如明文第 i 位 Data[i] 对应密码本单元格为 Book[x][y]，则明文第 i 位对应的密文为X Y，X和Y之间用空格隔开。
 *
 * 如果有多条密文，返回字符序最小的密文。
 *
 * 如果密码本无法匹配，返回"error"。
 *
 * 请你设计这个加密程序。
 *
 * 示例1：
 * 密码本：
 * 0 0 2
 * 1 3 4
 * 6 6 4
 *
 * 明文："3"，密文："1 1"
 *
 * 示例2：
 * 密码本：
 * 0 0 2
 * 1 3 4
 * 6 6 4
 *
 * 明文："0 3"，密文："0 1 1 1"
 *
 * 示例3：
 * 密码本：
 * 0 0 2 4
 * 1 3 4 6
 * 3 4 1 5
 * 6 6 6 5
 * 明文："0 0 2 4"，密文："0 0 0 1 0 2 0 3" 和 "0 0 0 1 0 2 1 2"，返回字典序最小的"0 0 0 1 0 2 0 3"
 * 明文："8 2 2 3"，密文："error"，密码本中无法匹配
 */

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class Encrpty {
    static boolean dfs(int u, int v, int k, int[][] Book, boolean[][] vist, List<Pair<Integer, Integer>> ans, int[] Data) {
        vist[u][v] = true;
        ans.add(new Pair<>(u, v));

        if (k == Data.length - 1) {
            return true;
        }

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        for (int i = 0; i < 4; ++i) {
            int x = u + dx[i];
            int y = v + dy[i];

            if (0 <= x && x < Book.length && 0 <= y && y < Book[0].length && !vist[x][y] && Book[x][y] == Data[k + 1]) {
                if (dfs(x, y, k + 1, Book, vist, ans, Data)) {
                    return true;
                }
            }
        }

        ans.remove(ans.size() - 1);
        vist[u][v] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] Data = new int[n];
        for (int i = 0; i < n; i++) {
            Data[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[][] Book = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                Book[i][j] = scanner.nextInt();
            }
        }

        boolean[][] vist = new boolean[m][m];
        List<Pair<Integer, Integer>> ans = new ArrayList<>();

        boolean find = false;
        for (int i = 0; i < m && !find; i++) {
            for (int j = 0; j < m; j++) {
                if (Data[0] == Book[i][j]) {
                    if (dfs(i, j, 0, Book, vist, ans, Data)) {
                        find = true;
                        break;
                    }
                }
            }
            if (find) break;
        }

        if (!find || ans.isEmpty()) {
            System.out.println("error");
        } else {
            for (Pair<Integer, Integer> p : ans) {
                System.out.print(p.getKey() + " " + p.getValue() + " ");
            }
            System.out.println();
        }

        // 使用nextLine()暂停程序
        scanner.nextLine();
    }
}

// 简单的Pair类实现
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}