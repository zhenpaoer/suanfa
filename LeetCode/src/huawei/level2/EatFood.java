package huawei.level2;

import java.util.ArrayList;
import java.util.List;

/**
 * 小华和小为是很要好的朋友，他们约定周末一起吃饭。通过手机交流，他们在地图上选择了多个聚餐地点（由于自然地形原因，部分聚餐地点不可达），求小为和小华都可以到达的聚餐地点有多少个？
 *
 * 输入
 * 第一行输入m,n，分别代表地图的长度和宽度
 * 第二行开始输入具体地图信息，其数字的含义如下：
 * 0 通畅的道路
 * 1 障碍物
 * 2 非障碍物，且为小华和小为的出发点
 * 3 非障碍物，被选中的聚餐地点
 *
 * 输出
 * 可以被两方都到达的聚餐地点数量
 *
 * 示例1
 * 输入
 * 4 4
 * 2 1 0 3
 * 0 1 2 1
 * 0 3 0 0
 * 0 0 0 0
 *
 * 输出
 * 2
 *
 * 说明
 * 图中两个位置3均可被小华和小为到达，故结果为2
 *
 * 示例2
 * 输入
 * 4 4
 * 2 1 2 3
 * 0 1 0 0
 * 0 1 0 0
 * 0 1 0 0
 *
 * 输出
 * 0
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class EatFood {
    //定义四个方向的偏移量（上下左右）
    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    //深度优先搜索函数
    private static boolean dfs(int currX, int currY, int targetX, int targetY, int[][] map, boolean[][][] visited, int person) {
        //如果当前位置就是目标位置，返回true
        if (currX == targetX && currY == targetY) {
            return true;
        }

        //遍历四个方向
        for (int[] dir : dirs) {
            int nextX = currX + dir[0], nextY = currY + dir[1];
            if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length || map[nextX][nextY] == 1 || visited[nextX][nextY][person]) {
                continue;
            }
            //标记下一个位置为已访问
            visited[nextX][nextY][person] = true;
            // 递归搜索下一个位置
            if (dfs(nextX, nextY, targetX, targetY, map, visited, person)) {

                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        //初始化输入
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] map = new int[m][n];
        //使用三维数组visited来记录每个人访问的位置
        boolean[][][] visited = new boolean[m][n][2];
        List<int[]> persons = new ArrayList<>();
        List<int[]> targets = new ArrayList<>();
        //读取地图信息，并记录小华小为的位置以及聚餐地点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = scanner.nextInt();
                if (map[i][j] == 2) {
                    persons.add(new int[]{i, j});
                } else if (map[i][j] == 3) {
                    targets.add(new int[]{i, j});
                }
            }
        }
        //获取小华和小为的位置
        int[] xiaohua = persons.get(0);
        int[] xiaowei = persons.get(1);
        int res = 0;
        //遍历所有聚餐地址
        for (int[] target : targets) {
            //重置visited数组
            visited = new boolean[m][n][2];
            //判断小华能否到达目标位置
            if (dfs(xiaohua[0], xiaohua[1], target[0], target[1], map, visited, 0)) {
                // 重置visitend数组
                visited = new boolean[m][n][2];
                //判断小为能否到达目标位置
                if (dfs(xiaowei[0], xiaowei[1], target[0], target[1], map, visited, 1)) {
                    //如果两个人都能到达目标位置，结果加1
                    res++;
                }
            }
        }

        System.out.println(res);
        scanner.close();
    }
}