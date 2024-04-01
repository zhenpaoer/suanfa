package src.suanfa.other;

import src.Node;

/**
 * 约瑟夫问题是个有名的问题：N个人围成一圈，
 * 从第一个开始报数，第M个将被杀掉，最后剩下一个，求最后一个人的编号
 * 其余人都将被杀掉。例如N=6，M=5，被杀掉的顺序是：5，4，6，2，3，1。
 * 例如N=6，M=4，被杀掉的顺序是：4,2,1,3,6,5
 * 旧序号 被杀的旧序号  新序号
 * 123456
 * 12356  4         345 12
 * 1356   2         4 123
 * 356    1          123
 * 56     3          23
 * 5      6         1
 *
 */
public class Josephus {
    public static void main(String[] args) {
        System.out.println(proccess(6, 4));
//        f(6, 5);
//        System.out.println(solve(6, 5));
    }


    /**
     * 推导
     * 1    2      3      4     5           6
     *                          5           6（6%6）      1（7%6）    2（8%6）     3（9%6）    //删除后
     *                          1           2            3            4          5          //重新排序号
     *                     （1+4）%6     （2+4）%6     （3+4）%6    （4+4）%6     （5+9）%6     //序号公式
     *
     *                                                                           5 （5%5）     1 （6%5）    2  （7%5）        3 （8%5）       4（9%5）
     *                                                                           1            2           3                4               5
     *                                                                      （1+4）%5     （2+4）%5     （3+4）%5         （4+4）%5         （5+4）%5 //序号公式
     *
     *   老序号 = （ 新序号 + m ）% n
     * @param n
     * @param m
     * @return
     */



    //假设 old 为删除之前的节点编号， new 为删除了⼀个节点之后的编号，则 old 与 new 之间的关系为
    //old = (new + m - 1) % n + 1。
    public static int proccess(int n, int m){
        if (n == 1){
            return n;
        }
        int old = (f(n - 1, m) + m ) % n ;
//        System.out.println(old);
        return old;
    }

    public static  int f(int n, int m){
        if(n == 1) return n;
        int i = (f(n - 1, m) + m ) % n ;
        System.out.println(i);
        return i;
    }

    public static int solve(int n, int m) {
        if(m == 1 || n < 2)
            return n;
        // 创建环形链表
        Node head = createLinkedList(n);
        // 遍历删除
        int count = 1;
        Node cur = head;
        Node pre = null;//前驱节点
        while (head.next != head) {
            // 删除节点
            if (count == m) {
                count = 1;
                pre.next = cur.next;
                cur = pre.next;
            } else {
                count++;
                pre = cur;
                cur = cur.next;
            }
        }
        return head.date;
    }
    static Node createLinkedList(int n) {
        Node head = new Node(1);
        Node next = head;
        for (int i = 2; i <= n; i++) {
            Node tmp = new Node(i);
            next.next = tmp;
            next = next.next;
        }
        // 头尾串联
        next.next = head;
        return head;
    }
}
