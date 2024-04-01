package src.suanfa.backtrace;

/**
 * ⼋皇后问题：8x8 的棋盘，希望往⾥放 8 个棋⼦（皇后），每个棋⼦所在的⾏、列、对⻆线都不
 * 能有另⼀个棋⼦
 */
public class eight_queens {
    private static Integer N = 8;
    //结果数 多少种摆法
    private static Integer result = 0;
    /**
     *
     * @param selectedColumns 已选解集合,下标表示⾏,值表示queen存储在哪⼀列
     * @param row 可选的空间解,第 n ⾏可选
     */
    public static void queenSettle(int[] selectedColumns, int row) {
        // 终⽌条件
        if (row > N - 1) {
            // 说明前 N ⾏都已经都选完皇后了，
            result += 1;
            printQueens(selectedColumns);
            return;
        }for (int i = 0; i < N; i ++) {
            // 剔除不合法的格⼦
            if (!isValid(row, i, selectedColumns)) {
                continue;
            }
            // 选择⼦节点（当前⾏）其中⼀个解
            selectedColumns[row] = i;
            // 选完之后再进⼊下个阶段的（下⼀⾏）遍历
            queenSettle(selectedColumns, row + 1);
            // 回溯,换⼀个解继续 dfs，回溯时要把回溯节点的解移除
            selectedColumns[row] = -1;
        }
    }
    /**
     * 判断相应的格⼦放置皇后是否OK
     * @param row
     * @param column
     * @param selectedColumns
     * @return
     */
    private static boolean isValid(int row, int column, int[] selectedColumns)
    {
        //判断row⾏column列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row-1; i >= 0; --i) { // 逐⾏往上考察每⼀⾏
            if (selectedColumns[i] == column) return false;
            // 第i⾏的column列有棋⼦吗？
            if (leftup >= 0) { // 考察左上对⻆线：第i⾏leftup列有棋⼦吗？
                if (selectedColumns[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对⻆线：第i⾏rightup列有棋⼦吗？
                if (selectedColumns[i] == rightup) return false;
            }
            --leftup; ++rightup;
        }return true;
    }
    public static void main(String[] args) {
        int[] selectedColumn = new int[N];
        // 从第 0 ⾏开始 DFS
//        queenSettle(selectedColumn, 0);
//        queenSet(selectedColumn,0);
        System.out.println("result="+result);
    }
    private static void printQueens(int[] result) { // 打印出⼀个⼆维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }






































}
