package huawei.level1;

import java.util.Arrays;
import java.util.Stack;

/**
 * 已知火星人使用的运算符为#、$，其与地球人的等价公式如下：
 * x#y = 4*x+3*y+2
 * x$y = 2*x+y+3
 * 1、其中x、y是无符号整数
 * 2、地球人公式按C语言规则计算
 * 3、火星人公式中，#的优先级高于$，相同的运算符，按从左到右的顺序计算
 * 现有一段火星人的字符串报文，请你来翻译并计算结果。
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class ET {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入火星报文：");
        String[] s = sc.nextLine().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");//数字断和非数字断
        System.out.println(Arrays.toString(s));
        int result = Translate(s);
        System.out.println("翻译后的结果为：" + result);
    }

    public static Stack<Integer> TmpNum = new Stack<>();
    public static Stack<Character> TmpOp = new Stack<>();
    public static Stack<Integer> stackNum = new Stack<>();
    public static Stack<Character> stackOp = new Stack<>();

    private static int Translate(String[] s) {

        for (int i = s.length - 1; i >= 0; i--) {
            String ele = s[i];
            if (ele.equals("#") || ele.equals("$")) {
                stackOp.push(ele.charAt(0));
            } else {
                stackNum.push(Integer.parseInt(ele));
            }
        }
        //开始写函数
        while (!stackOp.isEmpty()) {
            if (stackOp.peek() == '#') {
                if (stackNum.size() >= 2) {
                    stackOp.pop();//#出来
                    stackNum.push(hashCalculate(stackNum.pop(), stackNum.pop()));//将num头两个算完放回stacknum
                }

            } else if (stackOp.peek() == '$') {
                TmpOp.push(stackOp.pop());//将$放入tmp
                TmpNum.push(stackNum.pop());//从stcaknum取出一个放入tmpnum
            }
        }
        while (!TmpOp.isEmpty()) {
            if (TmpOp.pop() == '$') {
                //解决倒序
                System.out.println(TmpNum);//1 2     7
                System.out.println(stackNum);//26    26

                if (TmpNum.size() >= 2) {
                    stackNum.push(TmpNum.pop());//sn:26 2 ,tn:1
                }


                System.out.println(stackNum);//sn:26 2      26
                System.out.println(TmpNum);//tn:1          7

                stackNum.push(dollarCalculate(TmpNum.pop(), stackNum.pop()));//tn: ,sn:

                System.out.println("----" + stackNum);//sn:26 2
                System.out.println(TmpNum);//

                if (TmpNum.size() == 0 && stackNum.size() >= 2) {
                    TmpNum.push(stackNum.pop());
                }
                //存在倒序
                //eg：1$2$3#4,1去tn，$去to，2去tn，$去to，先算3#4=26存入sn;
                //算到$时，拿x=tn=2,y=sn=26,2$26=4+26+3=33存入sn;
                //再拿x=tn=1，y=sn=33，1$33=2+33+3=38.
                //正常：1$2$3#4,先算3#4=4*3+3*4+2=26
                //然后从左到右：1$2$26，先算1$2=2*1+2+3=7
                //最后7$26=2*7+26+3=43
            }
        }

        int result = stackNum.peek();
        return result;
    }

    private static Integer dollarCalculate(Integer pop, Integer pop1) {
        int x = pop;
        int y = pop1;
        int result = 2 * x + y + 3;
        return result;
    }

    private static Integer hashCalculate(Integer pop, Integer pop1) {
        int x = pop;
        int y = pop1;
        int result = 4 * x + 3 * y + 2;
        return result;
    }
}
