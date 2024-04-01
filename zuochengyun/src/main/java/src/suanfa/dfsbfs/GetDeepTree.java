package src.suanfa.dfsbfs;

import src.LeftRightNode;
import src.Node;

/**
 * 给定⼆叉树 [3,9,20,null,null,15,7] 找出其最⼤/最⼩深度。
 *     3
 *   / \
 *  9   20
 *      / \
 *     15  7
 */
public class GetDeepTree {
    public static void main(String[] args) {
        LeftRightNode head = new LeftRightNode(3);
        head.left = new LeftRightNode(9);
        head.right = new LeftRightNode(20);
        head.right.left = new LeftRightNode(15);
        head.right.right = new LeftRightNode(7);
        System.out.println(getDeepMax(head));
        System.out.println(getDeepMin(head));
        System.out.println(getDeepMin2(head));
    }

    private static int getDeepMax(LeftRightNode head){
        if (head == null){
            return 0;
        }
        int left = getDeepMax(head.left) + 1;
        int right = getDeepMax(head.right) + 1;
        return Math.max(left,right);
    }

    private static int getDeepMin(LeftRightNode head){
        if (head == null){
            return 0;
        }
        int left = getDeepMax(head.left) + 1;
        int right = getDeepMax(head.right) + 1;
        return Math.min(left,right);
    }
    private static int getDeepMin2(LeftRightNode head){
        if (head == null){
            return 0;
        }
        if (head.left == null){
            return 1+ getDeepMin2(head.right);
        }
        if (head.right == null){
            return 1+ getDeepMin2(head.left);
        }
        int deepMin2 = getDeepMin2(head.left);
        int deepMin21 = getDeepMin2(head.right);
        return 1+Math.min(deepMin2,deepMin21);
    }
}
