package src.suanfa.linklist;

import src.Node;

/**
 * 这其实是⼀道变形的链表反转题，⼤致描述如下
 * 给定2个单链表的头节点 head,head1,用来表示十进制整数相加，例如
 * 1234+34 = 1268
 * 4321
 * 43
 * 8621
 * 1268
 */
public class ReverseLinkList3 {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        Node head1 = new Node(4);
        head1.next = new Node(3);
        head = reverse(head);
        head1 = reverse(head1);
        Node add = add(head,head1);
        Node reverse = reverse(add);
        System.out.println(reverse);
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


    private static Node add2(Node head1,Node head2){
        Node  add = new Node(head1.date + head2.date);
        Node result = add;
        head2 = head2.next;
        head1  = head1.next;
        while (head1 != null && head2 != null){
            add.next = new Node(head1.date + head2.date);
            head1 = head1.next;
            head2 = head2.next;
            add = add.next;
        }
        if (head1.next == null){
            add.next = head2;
            return result;
        }
        if (head2.next == null){
            add.next = head1;
            return result;
        }
        return result;
    }



    private static Node add(Node head,Node head1){
        Node add = new Node(head.date + head1.date);
        Node result = add;
        head = head.next;
        head1  = head1.next;
        while (head != null && head1 != null){
            add.next = new Node(head.date + head1.date);
            head = head.next;
            head1  = head1.next;
            add = add.next;
        }
        if (head == null){
            add.next = head1;
            return result;
        }
        if (head1 == null){
            add.next = head;
            return result;
        }
        return result;
    }
}
