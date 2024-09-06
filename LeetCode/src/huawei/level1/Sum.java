package huawei.level1;

/**
 * 一个整数可以由连续的自然数之和来表示 给定一个整数
 * * 计算该整数有几种连续自然数之和的表达式
 * * 并打印出每一种表达式
 * * 输入描述
 * * 一个目标整数t 1<= t <=1000
 * * 输出描述
 * * 1.该整数的所有表达式和表达式的个数
 * * 如果有多种表达式，自然数个数最少的表达式优先输出
 * * 2.每个表达式中按自然数递增输出
 * * 具体的格式参见样例
 * * 在每个测试数据结束时，输出一行"Result:X"
 * * 其中X是最终的表达式个数
 * * 输入
 * * 9
 * * 输出
 * * 9=9
 * * 9=4+5
 * * 9=2+3+4
 * * Result:3
 * *
 * 说明 整数9有三种表达方法：
 * * 示例二
 *
 * 输入
 * * 10
 * * 输出
 * * 10=10
 * * 10=1+2+3+4
 * * Result:2
 */
public class Sum {
    public static void main(String[] args) {
        int a=10;
        int count=0;
        for(int j=1;j<a;j++) {
            for (int n = 1; n <=a; n++) {
                if((j*n+(1+j-1)*(j-1)/2)==a){
                    StringBuffer str=new StringBuffer(a+"=");
                    for(int m=0;m<j;m++){
                        if(m==0) {
                            str.append(n + m);
                        }else{
                            str.append("+"+(n + m));
                        }
                    }
                    System.out.println(str);
                    count++;
                }
            }
        }
        System.out.println("Result:"+count);
    }
}
