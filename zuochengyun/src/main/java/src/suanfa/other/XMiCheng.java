package src.suanfa.other;

/**
 * x的幂乘 输入X的n的幂乘，输出结果 中等难度
 *
 * 2^10 = 1024
 */
public class XMiCheng {
    public static void main(String[] args) {
        System.out.println(proccess(2.0,10));
        System.out.println(proccess2(2.0,10));
    }

    private static double proccess(double x , int n){
        if (x == -1){
            //n偶数
            if ( (n & 1) == 1 ){
                return 1;
            }else {
                return -1;
            }
        }
        if (x == 1.0){
            return 1;
        }
        if (n == -2147483648){
            return 0;
        }
        double res = 1;
        if (n > 0){
            for (int i = 0; i < n; i++) {
                res = res * x;
            }
        }else {
            n = -n;
            for (int i = 0; i < n; i++) {
                res = res * x;
            }
            res = 1/res;
        }
        return res;
    }

    /**
     * 迭代
     * 这里介绍种全新的解法，开始的时候受前边思路的影响，一直没理解。下午问同学，同学立刻想到了自己在《编程之美》看到的解法，这里分享下。
     *
     * 以 x 的 10 次方举例。10 的 2 进制是 1010，然后用 2 进制转 10 进制的方法把它展成 2 的幂次的和。
     *2 进制对应 1 0 1 0，我们把对应 1 的项进行累乘就可以了，而要进行累乘的项也是很有规律，前一项是后一项的自乘。
     * x^8 = x^4 * x^4我们可以从最右边一位，开始迭代。
     *
     * 这样话，再看一下下边的图，它们之间的对应关系就出来了。
     * @param x
     * @param n
     * @return
     */
    private static double proccess2(double x , int n){
        if (x == -1){
            //n偶数
            if ( (n & 1) == 1 ){
                return 1;
            }else {
                return -1;
            }
        }
        if (x == 1.0){
            return 1;
        }
        if (n > 0 ){
            return pow(x,n);
        }else {
            /**
             * 因为当 n = -2147483648 的时候我们无法正确计算，我们可以把
             * X^−2147483648 分解成 x^-2147483647 * x^-1 这样的话两部分都可以成功求解了
             */
            if (n == -2147483648){
                return proccess2(x,-2147483647) * 1/x;
            }
            return 1/proccess2(x, -n);
        }
    }

    private static double pow(double x,int n){
        double res = 1;
        while (n > 0){
            if ( (n & 1) == 1 ){
                res = res * x;
            }
            n >>= 1;
            x *= x;
        }
        return res;
    }
}
