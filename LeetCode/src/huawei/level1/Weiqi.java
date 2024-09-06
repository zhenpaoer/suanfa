package huawei.level1;

import java.util.Scanner;

/**
 * 围棋棋盘由纵横各19条线垂直相交组成，棋盘上一共19x19=361个交点，对弈双方一方执白棋，一方执黑棋，落子时只能将棋子置于交点上。
 * “气”是围棋中很重要的一个概念，某个棋子有几口气，是指其上下左右方向四个相邻的交叉点中，有几个交叉点没有棋子，由此可知：
 * <p>
 * 1、在棋盘的边缘上的棋子最多有3口气（黑1），在棋盘角点的棋子最多有2口气（黑2），其它情况最多有4口气（白1）
 * <p>
 * 2、所有同色棋子的气之和叫作该色棋子的气，需要注意的是，同色棋子重合的气点，对于该颜色棋子来说，只能计算一次气，比如下图中，黑棋一共4口气，而不是5口气，因为黑1和黑2中间红色三角标出的气是两个黑棋共有的，对于黑棋整体来说只能算一个气。
 * <p>
 * 3、本题目只计算气，对于眼也按气计算，如果您不清楚“眼”的概念，可忽略，按照前面描述的规则计算即可。
 * <p>
 * 现在，请根据输入的黑棋和白棋的坐标位置，计算黑棋和白棋一共各有多少气？
 * 输入描述：
 * 输入包括两行数据，如：
 * 0 5 8 9 9 10
 * 5 0 9 9 9 8
 * 1、每行数据以空格分隔，数据个数是2的整数倍，每两个数是一组，代表棋子在棋盘上的坐标；
 * 2、坐标的原点在棋盘左上角点，第一个值是行号，范围从0到18；第二个值是列号，范围从0到18。
 */
public class Weiqi {
    private static final int SIZE = 19;
    private static int[][] board = new int[SIZE][SIZE]; // 0: empty, 1: black, 2: white

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] blackInput = scanner.nextLine().split(" ");
        String[] whiteInput = scanner.nextLine().split(" ");

        // Place black stones
        for (int i = 0; i < blackInput.length; i += 2) {
            int row = Integer.parseInt(blackInput[i]);
            int col = Integer.parseInt(blackInput[i + 1]);
            board[row][col] = 1;
        }

        // Place white stones
        for (int i = 0; i < whiteInput.length; i += 2) {
            int row = Integer.parseInt(whiteInput[i]);
            int col = Integer.parseInt(whiteInput[i + 1]);
            board[row][col] = 2;
        }

        // Calculate liberties (breaths)
        int blackLiberties = calculateLiberties(1);
        int whiteLiberties = calculateLiberties(2);

        System.out.println("Black Liberties: " + blackLiberties);
        System.out.println("White Liberties: " + whiteLiberties);
    }

    private static int calculateLiberties(int stoneColor) {
        boolean[][] countedLiberties = new boolean[SIZE][SIZE];
        int liberties = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == stoneColor) {
                    // Check all four directions
                    liberties += checkLiberty(row - 1, col, countedLiberties);
                    liberties += checkLiberty(row + 1, col, countedLiberties);
                    liberties += checkLiberty(row, col - 1, countedLiberties);
                    liberties += checkLiberty(row, col + 1, countedLiberties);
                }
            }
        }

        return liberties;
    }

    private static int checkLiberty(int row, int col, boolean[][] countedLiberties) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == 0) {
            if (!countedLiberties[row][col]) {
                countedLiberties[row][col] = true;
                return 1;
            }
        }
        return 0;
    }
}

