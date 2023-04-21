package src.basic;


import src.class06.Code05_FindFirstIntersectNode;

/**
 * 两个单链表相交的一系列问题
 * [题目] 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返anull
 * [要求] 如果两个链表长度之和为N，时间复杂度请达到0(N)，额外空间复杂度
 * 请达到0(1)。
 */
public class FindFirstInsertNode {
    /**
     * 思路
     * 使用快慢指针，快指针走到空，就是无环，快慢指针相遇，就是有环。
     * 快慢指针第一次相遇之后，将快指针重置由头开始，慢指针在相遇处，同时再次出发，相遇的地方，就是环的入口。
     */
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }

    /**
     *
     *情况1：2个链表都是无环，只可能是2条线，或者y型线，不可能是x型，x型就是节点处next指针指向2个地方，这是不可能的。2个链表如果相交，那么他们end端一定是地址一样的，2个链表都遍历。如果相交，要找到节点处，长的列表先走 ∣ l e n l o n g − l e n s h o r t ∣ |len_{long}-len_{short}|∣len
     * long
     * ​
     *  −len
     * short
     * ​
     *  ∣ 步，然后一起走，一定会在交点处相遇。
     *
     * 情况2：一个为无环，一个有环，那么必然不想交；
     *
     * 情况3：2个都是有环，又分3种情况：
     * 情况3-1：2个不同的有环；
     * 情况3-2：入环节点是同一个，最好判断，分别找到入环节点，如果入环节点不同就是情况3-1或者3-3，如果入环节点相同，就使用上面的无环代码去找相交节点；
     * 情况3-3：入环节点不是同一个；让loop1继续走，在走会自己之前，判断会不会遇到loop2这个入口节点，遇到就是情况3-3，没有就是情况3-1；
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1,Node head2){
        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);
        if (loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }
        if (loop1 != null && loop2 != null){
            return BothLoop(head1,head2,loop1,loop2);
        }
        return null;
    }

    // 两个有环链表，返回第一个相交节点，如果不想交返回null
    public static Node BothLoop(Node head1,Node head2,Node loop1,Node loop2){
        Node cur1 ;
        Node cur2 ;
        if (loop1 != loop2){
            cur1 = loop1.next;
            while (loop1 != cur1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }else {
            cur1 = head1 ;
            cur2 = head2 ;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop1){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = head1 == cur1? head2:head1;
            n = Math.abs(n);
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;

        }
    }

    /**
     * 两个链表无环时，有两种情况，一是无交点，两条直线
     * 另一个情况是Y形状的两条链表，那么长链表走了len长度然后和短链表一起走，则是交点
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1,Node head2){
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0 ;
        while (cur1 != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null){
            n--;
            cur2 = cur2.next;
        }
        //情况一，终点不相等，代表无交点
        if (cur1 != cur2) {
            return null;
        }
        //长链表
        cur1 = n > 0 ? head1:head2;
        //短链表
        cur2 = cur1 == head1? head2:head1;
        n = Math.abs(n);
        //长链表先走n个长度
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
        //此时 长短链表长度一致，当相等时就是相交点
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }




    //获取环交点
    public static Node getLoop(Node head ){
        if (head == null || head.next == null ||head.next.next == null){
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        //定义一个快慢指针 快慢指针没有交点时说明无环，如果有交点说明有环儿，然后把快指针放到头结点
        //在快慢指针都走相同的步数，一定是环的交点
        while (slow != fast){
            if (fast.next == null || fast.next.next == null){
                //代表无环
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    
}
