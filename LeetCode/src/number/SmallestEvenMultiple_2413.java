package number;

/**
 * 给你一个正整数 n ，返回 2 和 n 的最小公倍数（正整数）。
 */
public class SmallestEvenMultiple_2413 {

    /**
     * 对于任意两个正整数 nn，mm 的最小公倍数为 \frac{n \times m}{\gcd(n, m)}
     * gcd(n,m)
     * n×m
     * ​
     *  ，其中 \gcd(n, m)gcd(n,m) 为 nn 和 mm 的最大公约数。
     *
     * 现在题目给出一个正数 nn，需要返回 22 和 nn 的最小公倍数，又因为任意正偶数与 22 的最大公约数为 22，任意正奇数与 22 的最大公约数为 11。所以当 nn 为偶数时直接返回 nn，否则返回 2 \times n2×n 即可。
     * @param args
     */
    public static void main(String[] args) {
        int a = 6;
        int b = 2;
        int c = a % 2 == 0 ? a : 2 *a ;
        System.out.println(c);
    }
}
