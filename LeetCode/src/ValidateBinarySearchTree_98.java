/**
 * Created by zhangzhen on 2020/5/5
 */

import javax.swing.tree.TreeNode;

/**给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * @Date 2020/5/5
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 **/
public class ValidateBinarySearchTree_98 {
	public static void main(String[] args) {
		TreeNode a1 = new TreeNode(3);
		a1.left = new TreeNode(1);
		a1.right = new TreeNode(4);
		System.out.println(isValidBST1(a1));
	}
	//中序遍历为升序

	public static boolean isValidBST1(TreeNode root) {
		return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public static boolean validate(TreeNode node, long min, long max) {
		if (node == null) {
			return true;
		}
		if (node.val <= min || node.val >= max) {
			return false;
		}
		return  validate(node.left, min, node.val) && validate(node.right, node.val, max);
	}
	public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
