package huawei.level2;

import java.util.Scanner;

/**
 * 小扇和小船今天又玩起来了数字游戏，
 * 小船给小扇一个正整数n (1<=n<=1e9)，
 * 小扇需要找到一个比n大的数字m，
 * 使得m和n对应的二进制中1的个数要相同
 * （如4对应二进制100,8对应二进制1000,1的个数都为1），
 * 现在求m的最小值。
 *
 * 输入2
 * 输出4
 *
 * 输入7
 * 输出11
 *
 *
 */
public class FindMNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("小船,请给我一个正整数n");
        int n = sc.nextInt();
        System.out.println("好的,小扇，我给的n是"+n+",请你开始找符合条件的m吧");
        int m = Caculate(n);
        System.out.println("我找到啦，符合条件的最小m是："+m);
    }
    private static int Caculate(int n) {
        System.out.println("当前数字n的二进制形式为："+Integer.toBinaryString(n));
        int count = Integer.bitCount(n);//位运算计算含1的数量
        System.out.println("当前数字n的二进制形式中1的数量为"+count);
        int m = n+1;
        //直接从比n大一的数开始找，一点点加，暴力破解
        while (Integer.bitCount(m)!=count){
            m++;
        }
        System.out.println("找到的数字m的二进制形式为："+Integer.toBinaryString(m));
        return m;
    }
}
