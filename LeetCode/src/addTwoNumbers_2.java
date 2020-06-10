/**
 * Created by zhangzhen on 2020/5/2
 */

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 把值存入int 在计算和
 **/
public class addTwoNumbers_2 {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(5);
//		l1.next = new ListNode(4);
//		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
//		l2.next = new ListNode(6);
//		l2.next.next = new ListNode(4);
//		final ListNode listNode = mergeTwoLists(l1, l2);
		ListNode listNode = addTwoNumbers(l1, l2);

		System.out.println(listNode);


	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummp = new ListNode(0);
		ListNode first = dummp;
		int carry = 0;
		while (l1 != null || l2!= null || carry!=0){
			int l1val = l1 == null ? 0 : l1.val;
			int l2val = l2 == null ? 0 : l2.val;
			int subNode = l1val + l2val + carry;
			carry = subNode / 10 ; //carry是进位；
			ListNode subnode = new ListNode(subNode % 10); //求模
			first.next = subnode;
			first = first.next;
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
		}

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
