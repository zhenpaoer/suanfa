/**
 * Created by zhangzhen on 2020/5/7
 */

/**
 * @Date 2020/5/7
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值
 *
 **/
public class SubtreofAnotherTree_572 {
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null) return false;
		//t是s的子树，要么t等于s，要么t等于s的左/右子树。
		return subFrom(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	public boolean subFrom(TreeNode s, TreeNode t){
		if (s == null && t == null) return true;
		if (s == null || t == null) return false;
		if (s.val != t.val) return false;
		return subFrom(s.left, t.left) && subFrom(s.right, t.right);
	}
	public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
