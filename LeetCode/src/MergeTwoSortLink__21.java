/**
 * Created by zhangzhen on 2020/5/1
 */

import java.util.List;

/**
 *将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 **/
public class MergeTwoSortLink__21 {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);

		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
//		final ListNode listNode = mergeTwoLists(l1, l2);
		final ListNode listNode = train(l1, l2);

		System.out.println(listNode);
	}


	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummpy = new ListNode(-1);
		ListNode prev = dummpy;
		while (l2 !=null && l1 !=null ){
			if (l1.val < l2.val){
				prev.next = l1;
				l1 = l1.next;
			}else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}
		if (l1 != null) prev.next=l1;
		if (l2 != null) prev.next=l2;

		return dummpy.next;

	}

	//自己实现
	public static ListNode train(ListNode l1, ListNode l2){
		ListNode dummp = new ListNode(0);
		ListNode prev = dummp;
		//注意是&&，当均不为空的时候走循环，只要有一个是null则退出循环
		while (l1 != null && l2 != null){
			//判断如果 l1的值小，则prev的下一个节点就是l1，l1移动一个节点
			if (l1.val<l2.val){
				prev.next = l1;
				l1 = l1.next;
			}else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}
		if (l1 != null) prev.next = l1;
		if (l2 != null) prev.next = l2;

		return dummp.next;
	}


	//在实现一次
	public static ListNode train2(ListNode l1,ListNode l2){
		ListNode dummp = new ListNode(0);
		ListNode cur = dummp;
		while (l1 !=null && l2 != null){
			if (l1.val<l2.val){
				cur.next = l1;
				l1 = l1.next;
			}else {
				cur.next =l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}
		while ( l1 !=null) cur.next = l1;
		while ( l2 !=null) cur.next = l2;

		return dummp.next;
	}

	public static class ListNode {
     int val;
     ListNode next;
     ListNode(){}
     ListNode(int x) { val = x; }

     //输出node的值
		@Override
		public String toString() {
     		ListNode node = this;
			StringBuilder sb= new StringBuilder();
			while (node !=null){
				sb.append(node.val +" ");
				node = node.next;
			}
			return sb.toString();
		}
	}
}
