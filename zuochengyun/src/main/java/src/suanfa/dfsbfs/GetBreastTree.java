package src.suanfa.dfsbfs;

import src.LeftRightNode;

import java.util.ArrayList;
import java.util.List;

/**
 * : 给你⼀个⼆叉树，请你返回其按层序遍历得到的节点值。 （即逐层地，从左到右访
 * 问所有节点）。 示例，给定⼆叉树：[3,9,20,null,null,15,7]
 *     3
 *   / \
 *  9   20
 *      / \
 *     15  7
 *
 *     返回
 *[
 [3],
 [9,20],
 [15,7]
 ]
 */
public class GetBreastTree {
    private static final List<List<Integer>> TRAVERSAL_LIST = new ArrayList<>();
    public static void main(String[] args) {
        LeftRightNode head = new LeftRightNode(3);
        head.left = new LeftRightNode(9);
        head.right = new LeftRightNode(20);
        head.right.left = new LeftRightNode(15);
        head.right.right = new LeftRightNode(7);
        getBreastByDFS(head,0);
        System.out.println(TRAVERSAL_LIST);
    }

    /**
     *  DFS 可以⽤递归来实现，其实只要在递归函数上加上⼀个「层」的变
     * 量即可，只要节点属于这⼀层，则把这个节点放⼊相当层的数组⾥，
     * @param root
     * @return
     */
    private static void getBreastByDFS(LeftRightNode root,int level){
        if (root == null){
            return ;
        }
        if(TRAVERSAL_LIST.size() < level + 1){
            TRAVERSAL_LIST.add(new ArrayList<>());
        }
        List<Integer> integers = TRAVERSAL_LIST.get(level);
        integers.add(root.value);

        getBreastByDFS(root.left,level+1);
        getBreastByDFS(root.right,level+1);
    }
}
