package huawei.level2;

import java.util.*;

/**
 * 马是象棋(包括中国象棋和国际象棋)中的棋子，走法是每步直一格再斜一格，即先横着或直着走一格，然后再斜着走一个对角线，可进可退，可越过河界，俗称“马走’日’字。给顶m行n列的棋盘(网格图)，棋盘上只有有棋子象棋中的棋子“马"，并且每个棋子有等级之分，等级为k的马可以跳1-k步(走的方式与象棋中“马”的规则一样，不可以超出棋盘位置)，问是否能将所有马跳到同-位置，如果存在，输出最少需要的总步数(每匹马的步数相加)，不存
 * 在则输出-1。注:允许不同的马在跳的过程中跳到同一位置，坐标为(x,y)的马跳一次可以跳到到坐标为(x+1.y+2).(x+1.y-2),(x+2.y+1).(x+2.y-1).(x-1.y+2),(x-1,y-2),(x-2,y+1).(x-2.y-1),的格点上，但是不可以超出棋盘范围。
 *
 * 输入描述：
 * 第一行输入m,n代表m行n列的网格图棋盘(1≤m,n≤25);
 * 接下来输入m行n列的网格图棋盘，如果第i行,第j列的元素为"."代表此格点没有棋子，如果为数字k(1<=k<=9)，代表此格点存在等级为K的“马”。
 *
 * 输出描述
 * 输出最少需要的总步数(每匹马的步数相加)，不存在则输出-1.
 *
 * 示例 1
 * 输入
 * 3 2
 * . .
 * 2.
 * . .
 * 输出
 * 0
 *
 * 示例 2
 * 输入
 * 3 5
 * 47.48
 * 4744.
 * 7…
 * 输出
 * 17
 */

import java.util.*;

public class MaZouRi {

    // 定义马的八个移动方向
    private static final int[][] MOVES = {
            {1, 2}, {1, -2}, {2, 1}, {2, -1},
            {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}
    };


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            scanner.nextLine(); // Consume the rest of the line

            int[][] board = new int[m][n];
            List<int[]> horses = new ArrayList<>();

            // 读取棋盘
            for (int i = 0; i < m; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < n; j++) {
                    char c = line.charAt(j);
                    if (c != '.') {
                        board[i][j] = c - '0'; // 将字符转为整数
                        horses.add(new int[]{i, j, board[i][j]}); // 存储马的位置和等级
                    }
                }
            }

            // 检查每个可能的位置作为目标
            int minSteps = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int totalSteps = 0;
                    boolean isPossible = true;

                    // 对每匹马计算到目标位置的最短步数
                    for (int[] horse : horses) {
                        int x = horse[0];
                        int y = horse[1];
                        int level = horse[2];

                        // 计算从 (x, y) 到 (i, j) 的最短步数
                        int steps = bfs(x, y, i, j, level, m, n);
                        if (steps == -1) {
                            isPossible = false;
                            break;
                        }
                        totalSteps += steps;
                    }

                    if (isPossible) {
                        minSteps = Math.min(minSteps, totalSteps);
                    }
                }
            }

            // 输出结果
            System.out.println(minSteps == Integer.MAX_VALUE ? -1 : minSteps);
        }
    }

    // 使用 BFS 计算最短步数
    private static int bfs(int startX, int startY, int targetX, int targetY, int level, int m, int n) {
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        dist[startX][startY] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int d = dist[x][y];

            if (x == targetX && y == targetY) {
                return d;
            }

            for (int[] move : MOVES) {
                int nx = x + move[0];
                int ny = y + move[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && dist[nx][ny] == -1 && Math.abs(move[0]) + Math.abs(move[1]) <= level) {
                    dist[nx][ny] = d + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return -1;
    }

}
