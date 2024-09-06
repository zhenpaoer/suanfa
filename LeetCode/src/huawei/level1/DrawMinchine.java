package huawei.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。1. 尝试沿着横线坐标正向绘制直线直到给定的终点E2. 期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移,为负数表示负向偏移给定的横坐标终点值E 以及若干条绘制指令，请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。输入描述首行为两个整数 N 和 E表示有N条指令,机器运行的横坐标终点值E接下来N行 每行两个整数表示一条绘制指令x offsetY用例保证横坐标x以递增排序的方式出现且不会出现相同横坐标x取值范围0<N<=100000<=x<=E<=20000-10000<=offsetY<=10000
 *
 * 输入描述
 * 首行为两个整数 N 和 E
 * 表示有N条指令,机器运行的横坐标终点值E
 * 接下来N行 每行两个整数表示一条绘制指令x offsetY
 * 用例保证横坐标x以递增排序的方式出现
 * 且不会出现相同横坐标x
 * 输出描述
 * 一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。
 * 输入	4 10 1 1 2 1 3 1 4 -2
 * 输出	12
 * 输入	2 4 0 1 2 -2
 * 输出	4
 */
public class DrawMinchine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        int N = Integer.parseInt(split[0]);//N条指令
        int E = Integer.parseInt(split[1]);//终点值
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
        System.out.println(calculateArea(E, list));
    }

    private static int calculateArea(int e, List<int[]> list) {
        //初始化面积大小
        int area = 0;
        //上一个节点的Y轴的值
        int preY = 0;
        for (int i = 0; i < list.size() -1; i++) {
            int[] coordinate = list.get(i);
            //长度直接求两个坐标X轴差值
            int x = list.get(i + 1)[0] - coordinate[0];
            //高度，根据上一个节点的Y坐标的值进行累加
            int y = coordinate[1] + preY;
            //高度可能为负数，但是面积不能为负，所以需要取绝对值
            area += x*Math.abs(y);
            preY = y;
        }
        //计算最后一个坐标点和目标点的面积
        int[] last = list.get(list.size() - 1);
        if(last[0] < e){
            int x = e - last[0];
            int y = Math.abs(last[1] + preY);
            area += x*y;
        }
        return area;
    }

}
