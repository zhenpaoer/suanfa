import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * 示例 1：
 * 输入：["CQueue","appendTail","deleteHead","deleteHead"]   [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * 输入：["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]   [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 */

public class TwoStacksImplementQueues {
	LinkedList<Integer> stact1 ;
	LinkedList<Integer> stact2 ;

	public TwoStacksImplementQueues() {
		stact1 = new LinkedList<Integer>();
		stact2 = new LinkedList<Integer>();
	}

	public void appendTail(int value) {
		stact1.add(value);
	}

	public int deleteHead() {
		if (stact2.isEmpty()){
			if (stact1.isEmpty()) return -1;
			while (!stact1.isEmpty()){
				stact2.add(stact1.pop());
			}
			return stact2.pop();
		}else return stact2.pop();
	}

	public static void main(String[] args) {
		TwoStacksImplementQueues twoStacksImplementQueues = new TwoStacksImplementQueues();
		System.out.println(twoStacksImplementQueues.deleteHead());
		twoStacksImplementQueues.appendTail(3);
		System.out.println(twoStacksImplementQueues.deleteHead());
		System.out.println(twoStacksImplementQueues.deleteHead());
	}
}
