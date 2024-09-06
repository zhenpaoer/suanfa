package huawei.level1;

/**
 * RSA加密算法在网络安全世界中无处不在，它利用了极大整数因数分解的困难度，数据越大，安全系数越高。
 *
 * 给定一个32位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
 *
 * 输入
 *
 * 一个正整数num
 *
 * 0 < num <= 2147483647
 *
 * 输出
 *
 * 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1 -1。
 * 输入15
 * 输出3 5
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class nF {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num = scanner.nextInt();
        List<Integer> factors = factorize(num);
        if (factors.size() == 2) {
            System.out.println(factors.get(0) + " " + factors.get(1));
        } else {
            System.out.println("-1 -1");
        }
    }

    public static List<Integer> factorize(int num) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(num); i++) {
            while (num % i == 0) {
                factors.add(i);
                num /= i;
            }
        }
        if (num > 1) {
            factors.add(num);
        }
        return factors;
    }

}

