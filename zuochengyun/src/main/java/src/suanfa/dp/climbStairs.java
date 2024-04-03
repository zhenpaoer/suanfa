package src.suanfa.dp; /**
 * Created by zhangzhen on 2020/5/1
 */

/**
 * n个台阶，每次走1或者2次，有多少种走法
 **/
public class climbStairs {
    private static int climbStairs2(int n) {
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * 滚动数组，
     * <p>
     * 滚动数组是DP中的一种编程思想。简单的理解就是让数组滚动起来，每次都使用固定的几个存储空间，来达到压缩，
     * 节省存储空间的作用。起到优化空间，主要应用在递推或动态规划中（如01背包问题）。因为DP题目是一个自底向上的扩展过程，我们常常需要用到的是连续的解，前面的解往往可以舍去。所以用滚动数组优化是很有效的。利用滚动数组的话在N很大的情况下可以达到压缩存储的作用。是用时间去换空间的
     * <p>
     * 上面这个循环d[i]只依赖于前两个数据d[i - 1]和d[i - 2]; 为了节约空间用滚动数组的做法，可以将整个dp数组压缩成dp[3]
     *
     * @param n
     * @return
     */
    private static int climbStairs3(int n) {
        int[] arr = new int[3];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 2; i < n; i++) {
            arr[0] = arr[1];
            arr[1] = arr[2];
            arr[2] = arr[0] + arr[1];
        }
        return arr[2];
    }

    /**
     * 矩阵相乘(矩阵快速幂)
     * Solution5叫做 Binets Method，它利用数学归纳法证明了一下，这里就直接用了，至于怎么想出来的，我也不清楚了。
     *
     * 定义一个矩阵 $Q =
     * 11 10
     * 11 10
     * $ ，然后求 f ( n ) 话，我们先让 Q 矩阵求幂，然后取第一行第一列的元素就可以了，也就是
     * f(n)=Q^n[0][0]
     * @param n
     * @return
     */
    /**
     *面试提问进一步优化：矩阵快速幂 详解
     * 方法二前置知识
     * 快速幂算法原理:
     * 如需求数据 a 的幂次，此处 a 可以为数也可以为矩阵，常规做法需要对a进行不断的乘积即 a * a * a * ... 此处的时间复杂度将为 O(n)
     * 以求 3^10 的结果为例：
     * [优化步骤1：]
     * 易知：
     * 3^10=3*3*3*3*3*3*3*3*3*3
     *     =9^5 = 9^4*9
     *     =81^2*9
     *     =6561*9
     * 基于以上原理，我们在计算一个数的多次幂时，可以先判断其幂次的奇偶性，然后：
     * 如果幂次为偶直接 base(底数) 作平方，power(幂次) 除以2
     * 如果幂次为奇则底数平方，幂次整除于2然后再多乘一次底数
     *
     * [优化步骤2：]
     * 对于以上涉及到 [判断奇偶性] 和 [除以2] 这样的操作。使用系统的位运算比普通运算的效率是高的，因此可以进一步优化：
     * 把 power % 2 == 1 变为 (power & 1) == 1
     * 把 power = power / 2 变为 power = power >> 1
     *
     * 方法二：矩阵快速幂。时间O(logn),空间O(1):
     * 根据递推关系，我们可以得到：对于如下矩阵运算，有 c = a^n * b，其中：
     * a = [[1,1], [1,0]],
     * b = [F(1), F(0)],
     * c = [F(n+1), F(n)]
     *
     * @param n
     * @return
     */
    class Solution {
        public int fib(int n) {
            //矩阵快速幂
            if (n < 2) {
                return n;
            }
            //定义乘积底数
            int[][] base = {{1, 1}, {1, 0}};
            //定义幂次
            int power = n - 1;
            int[][] ans = calc(base, power);
            //按照公式，返回的是两行一列矩阵的第一个数
            return ans[0][0];
        }
        //定义函数,求底数为 base 幂次为 power 的结果
        public int[][] calc(int[][] base, int power) {
            //定义变量，存储计算结果，此次定义为单位阵
            int[][] res = {{1, 0}, {0, 1}};
            //可以一直对幂次进行整除
            while (power > 0) {
                //1.若为奇数，需多乘一次 base
                //2.若power除到1，乘积后得到res
                //此处使用位运算在于效率高
                if ((power & 1) == 1) {
                    res = mul(res, base);
                }
                //不管幂次是奇还是偶，整除的结果是一样的如 5/2 和 4/2
                //此处使用位运算在于效率高
                power = power >> 1;
                base = mul(base, base);
            }
            return res;
        }
        //定义函数,求二维矩阵：两矩阵 a, b 的乘积
        public int[][] mul(int[][] a, int[][] b) {
            int[][] c = new int[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    //矩阵乘积对应关系，自己举例演算一遍便可找到规律
                    c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
                }
            }
            return c;
        }
    }

    public static int climbStairs(int n) {
        int[][] Q = {{1, 1}, {1, 0}};
        int[][] res = pow(Q, n);
        return res[0][0];
    }

    public static int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            //最后一位是 1，加到累乘结果里
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            //n 右移一位
            n >>= 1;
            //更新 a
            a = multiply(a, a);
        }
        return ret;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }


    public static void main(String[] args) {
        System.out.println(climbStairs2(10));
        System.out.println(climbStairs(10));
        System.out.println(climbStairs3(10));
    }
}
