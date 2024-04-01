package src.suanfa.linklist;

import src.Node;

/**
 * 这其实是⼀道变形的链表反转题，⼤致描述如下
 * 给定⼀个单链表的头节点 head,实现⼀个调整单链表的函数，使得每K个节点之间为⼀组进⾏逆序，并
 * 且从链表的尾部开始组起，头部剩余节点数量不够⼀组的不需要逆序。（不能使⽤队列或者栈作为辅
 * 助）
 * 例如： 链表:1->2->3->4->5->6->7->8->null, K = 3。那么 6->7->8，3->4->5，1->2各位⼀组。调整后：
 * 1->2->5->4->3->8->7->6->null。其中 1，2不调整，因为不够⼀组。
 * 87654321
 * 67834521
 * 12543876
 */
public class ReverseLinkList2 {
    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next.next = new Node(8);
        head1 = reverse(head1);
        head1 = reverseGroup(head1,3);
        head1 = reverse(head1);
        System.out.println(head1);
    }

//    //反转
    private static Node reverse(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node newList = reverse(head.next);
        Node t1 = head.next;
        t1.next = head;
        head.next = null;
        return newList;
    }

    // 876 54321 ---> 678 54321
    // 678 34521
        private static Node reverseGroup(Node head,int k){
        Node temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null){
            return head;
        }
        Node t2 = temp.next;
        temp.next = null;
        Node newHead = reverse(head);
        Node node = reverseGroup(t2, k);
        head.next = node;

        return newHead;
    }

}
