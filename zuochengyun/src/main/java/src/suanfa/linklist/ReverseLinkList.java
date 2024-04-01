package src.suanfa.linklist;

import src.Node;

/**
 * 反转单链表。例如链表为：1->2->3->4。反转后为 4->3->2->1
 */
public class ReverseLinkList {
    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        Node reverse = reverse(head1);
        System.out.println(reverse);
    }
    
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
}
