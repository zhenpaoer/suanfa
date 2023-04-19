package node;

/**
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 *
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 *
 */

/**
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 */
public class MaximumDifferenceBetweenNodeandAncestor_1026 {

    public static void main(String[] args) {
        TreeNode head = new  TreeNode(8);
        head.left = new TreeNode(3);
        head.right = new TreeNode(10);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(6);
        head.left.right.left = new TreeNode(4);
        head.left.right.right = new TreeNode(7);
        head.right.right = new TreeNode(14);
        head.right.right.left = new TreeNode(13);
        System.out.println(proccess(head));
    }

    public static int proccess(TreeNode node){
        int max = getMaxValue(node, node.val,node.val);

        return max;
    }

    public static int getMaxValue(TreeNode node,int max ,int min){
        if (node.val > max){
            max  = node.val;
        }
        if (node.val < min){
            min = node.val;
        }
        if (node.left == null && node.right == null){
            return max-min;
        }
        int left = 0;
        int right = 0;
        if (node.left != null){
            left = getMaxValue(node.left,max, min);
        }
        if (node.right != null){
            right =  getMaxValue(node.right, max, min);
        }
        int res = left > right ? left:right;
        return res;

    }

    public static  int abs(int b){
       return  (b ^ (b >> 31)) - (b >> 31) ;
    }



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
