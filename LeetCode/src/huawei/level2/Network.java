package huawei.level2;

import java.util.Vector;

/**
 * 在一个机房中，服务器的位置标识在 n*m 的整数矩阵网格中，1 表示单元格上有服务器，0 表示没有。如果两台服务器位于同一行或者同一列中紧邻的位置，则认为它们之间可以组成一个局域网。
 * 请你统计机房中最大的局域网包含的服务器个数。
 *
 * 输入描述:
 * 第一行输入两个正整数，n和m，0<n,m<=100
 * 之后为n*m的二维数组，代表服务器信息
 * 输出描述:
 * 最大局域网包含的服务器个数。
 * 示例1
 *
 * 输入
 * 2 2
 * 1 0
 * 1 1
 * 输出
 * 3
 */
import java.util.Scanner;
import java.util.Vector;

public class Network {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static boolean isValid(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static void dfs(Vector<Vector<Integer>> grid, boolean[][] visited, int x, int y, int[] count) {
        visited[x][y] = true;
        count[0]++;

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isValid(newX, newY, grid.size(), grid.get(0).size()) && grid.get(newX).get(newY) == 1 && !visited[newX][newY]) {
                dfs(grid, visited, newX, newY, count);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Vector<Vector<Integer>> grid = new Vector<>();
        for (int i = 0; i < n; i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < m; j++) {
                row.add(scanner.nextInt());
            }
            grid.add(row);
        }

        boolean[][] visited = new boolean[n][m];
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid.get(i).get(j) == 1 && !visited[i][j]) {
                    int[] count = {0}; // 使用数组来传递引用
                    dfs(grid, visited, i, j, count);
                    maxCount = Math.max(maxCount, count[0]);
                }
            }
        }

        System.out.println(maxCount);
        scanner.close();
    }
}