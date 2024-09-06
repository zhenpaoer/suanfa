package huawei.level1;

import java.util.Scanner;

/**
 * 有位客人来自异国，在该国使用m进制计数。该客人有个幸运数字n(n<m)，
 * 每次购物时，其总是喜欢计算本次支付的花费(折算为异国的价格后)中存在多少幸运数字。
 * 问：当其购买一个在我国价值k的产品时，其中包含多少幸运数字？
 *
 * 输入描述
 * 第一行输入为 k, n, m。
 *
 * 其中：
 *
 * k 表示 该客人购买的物品价值（以十进制计算的价格）
 * n 表示 该客人的幸运数字
 * m 表示 该客人所在国度的采用的进制
 * 输出描述
 * 输出幸运数字的个数，行末无空格。当输入非法内容时，输出0
 *
 * 用例1
 * 输入：
 *
 * 10 2 4
 *
 * 输出：
 *
 * 2
 *
 * 说明：
 *
 * 10用4进制表示时为22，同时，异国客人的幸运数字是2，故而此处输出为2，表示有2个幸运数字。
 *
 * 用例2
 * 输入：
 *
 * 10 4 4
 *
 * 输出：
 *
 * 0
 *
 * 说明：
 *
 * 此时客人的幸运数字为4，但是由于该国最大为4进制，故而在该国的进制下不可能出现幸运数字，故而返回0
 */
public class Foreigner {


    public static void main(String[] args) {
        // 定义变量 k 用于存储物品价格（十进制），n 存储幸运数字，m 存储异国进制
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] arr = s.split(" ");
        int k = Integer.parseInt(arr[0]);
        int n = Integer.parseInt(arr[1]);
        int m = Integer.parseInt(arr[2]);
        System.out.println(getResult(k, n, m));
    }
    public static int getResult(int k,int n,int m){
        // 判断输入的有效性：价格和幸运数字不应小于0，且幸运数字必须小于进制数，进制数应大于1
        if (k < 0 || n < 0 || m <= 1 || n >= m) {
            return 0;
        }
        int count = 0; // 初始化幸运数字出现次数为0
        while (k > 0) { // 当物品价格转换后仍有剩余数值时
            if (k % m == n) { // 如果剩余数值对m取余等于幸运数字
                count++; // 增加幸运数字出现的计数
            }
            k /= m; // 将物品价格转换为对应进制下的一位数值
        }
        // 输出幸运数字出现的次数
        return count;
    }
}
