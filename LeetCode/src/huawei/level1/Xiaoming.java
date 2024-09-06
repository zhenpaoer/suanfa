package huawei.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 小明在玩一个游戏，游戏规则如下:在游戏开始前，小明站在坐标轴原点处(坐标值为0)给定一组指令和一个幸运数，每个指令都是一个整数，小明按照指定的要求前进或者后退指定的步数。前进代表朝坐标轴的正方向走，后退代表朝坐标轴的负方向走，幸运数为一个整数，如果某个指令正好和幸运数相等，则小明行进步数加 1。
 *
 * 例如: 幸运数为 3，指令内[ 2 , 3 , 0 , −5 ]
 * 指令为 2，表示前进 2步
 * 指令为 3 正好好和幸运数相等，前进 3+1=4步
 * 指令为 0,表示原地不动，既不前进，也不后退
 * 指令为 5,表示后退 5步。
 * 请你计算小明在整个游戏过程中，小明所处的最大坐标值。
 *
 * 输入描述：
 * 第一行输入 1 个数字，代表指令的总个数 n ( 1≤n≤100)
 * 第二行输入 1 个数字，代表幸运数 m ( −100≤m≤100)
 * 第三行输入 n 个指令，每个指令值的取值范围为: −100≤指令值≤100
 *
 * 输出描述：
 * 输出在整个游戏过程中，小明所处的最大坐标值。异常情况下输出：12345
 *
 * 示例1：
 * 输入：
 * 2
 * 1
 * -5 1
 *
 * 输出：
 * 0
 *
 * 说明：
 * 总共 2 个指令，幸运数为 1 ，依照指令行进，依次如下游戏开始前，站在坐标轴原点，此时坐标值为 0;
 * 指令为 −5 ，后退5 步 ，此时坐标值为−5 ;
 * 指令为 1，正好等于幸运数，前进 1+1=2步，此时坐标值为 −3;
 * 整个游戏过程中，小明所处的坐标值依次为[0,−5,−3]，最大坐标值为 0。
 *
 * 示例2：
 * 输入：
 * 5
 * -5
 * -5 1 6 0 -7
 *
 * 输出：
 * 1
 *
 * 说明：
 * 总共 5 个指令，幸运数为 −5，依照指令行进，依次如下:
 * 游戏开始前，站在坐标轴原点，此时坐标值为 0，
 * 指令为 −5，正好等于幸运数，后退 5+1=6步，此时坐标值为 −6;
 * 指令为 1，前进 1 步此时坐标值为 −5 ;
 * 指令为 6 ，前进 6 步此时坐标值为1 ;
 * 指令为 0 ，既不前进也不后退，此时坐标值为 1 ;
 * 指令为 −7，后退 7步，此时坐标值为 −6。
 * 整个游戏过程中，小明所处的坐标值依次为 [0,−6,−5,1,1,−6]，最大坐标值为 1。
 */
public class Xiaoming {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 异常输入处理
        if (n < 1 || n > 100 || m < -100 || m > 100) {
            System.out.println("12345");
            return;
        }

        List<Integer> instructions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int inst = scanner.nextInt();
            if (inst < -100 || inst > 100) {
                System.out.println("12345");
                return;
            }
            instructions.add(inst);
        }

        List<Integer> coords = new ArrayList<>();
        int currCoord = 0;
        for (int inst : instructions) {
            if (inst == m && inst >= 0) {
                currCoord += (inst + 1);
            } else if (inst == m && inst < 0) {
                currCoord += (inst - 1);
            } else {
                currCoord += inst;
            }
            coords.add(currCoord);
        }

        // 查找并输出最大坐标值，如果coords为空则输出错误码
        if (coords.isEmpty()) {
            System.out.println("Error in tracking coordinates.");
            return;
        }

        int maxCoord = Collections.max(coords);

        System.out.println(maxCoord);

        // 注意：Java中通常不使用system("pause")，因为它不是跨平台的，这里假设我们不需要暂停
        // 如果你需要在某些IDE中看到结果后再关闭窗口，可以考虑添加一行空白的输入扫描
        // scanner.nextLine(); // 这只是为了演示如何暂停程序结束，实际应用中根据需要决定

        scanner.close();
    }
}
