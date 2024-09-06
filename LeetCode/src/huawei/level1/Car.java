package huawei.level1;

import jdk.nashorn.internal.ir.CallNode;

import java.util.Scanner;

/**
 * 特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车.车辆大小不一，小车占一个车位(长度1)，货车占两个车位(长度2)，卡车占三个车位(长度3)。
 *
 * 统计停车场最少可以停多少辆车，返回具体的数目。
 *
 * 输入描述
 *
 * 整型字符串数组cars[]，其中1表示有车，0表示没车，数组长度小于1000。
 *
 * 输出描述
 *
 * 整型数字字符串，表示最少停车数目。
 * 输入1,0,1
 * 输出2
 */
public class Car {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }
    public  static int getResult(String s){
        String replace = s.replace(",", "").replace("111", "x").replace("11", "x").replace("1", "x");
        String[] split = replace.split("");
        int count=0;
        for (String s1 : split) {
            if(s1.equals("x"))
                count++;
        }
        return count;
    }
}
