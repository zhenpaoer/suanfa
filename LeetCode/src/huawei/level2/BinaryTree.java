package huawei.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 给出一个二叉树如下图所示：
 * 6
 * /  \
 * 7      9
 * \   /
 * -2 6
 * <p>
 * 请由该二叉树生成一个新的二叉树，
 * 它满足其树中的每个节点将包含原始树中的左子树和右子树的和。
 * <p>
 * 20(7-2+9+6)
 * /      \
 * -2        6
 * \      /
 * 0   0
 * 左子树表示该节点左侧叶子节点为根节点的一颗新树；
 * 右子树表示该节点右侧叶子节点为根节点的一颗新树。
 */
public class BinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public static TreeNode arrayToTreeNode(int[] array) {//数组转换二叉树
        if (array.length == 0) {
            return null;
        }
        //将数组array的头元素放在树的根节点中
        TreeNode root = new TreeNode(array[0]);
        //创建一个链表队列存放树节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);//队列加入根节点
        boolean isLeft = true;//先左后右
        for (int i = 1; i < array.length; i++) {
            TreeNode node = queue.peek();//从队列中获取但不移除头部节点
            //eg.当i=1时，当前node=queue.peek()=root
            //当前isLeft=true，给node.left也就是root的左孩子赋值为array[i]
            if (isLeft) {
                if (array[i] != 0) {
                    node.left = new TreeNode(array[i]);
                    queue.offer(node.left);//将root的左孩子加入队列
                }
                isLeft = false;//将是左置为否，表明有左孩子了
            } else {
                if (array[i] != 0) {
                    node.right = new TreeNode(array[i]);
                    queue.offer(node.right);
                }
                queue.poll();//移除当前处理节点root
                isLeft = true;
            }
        }
        return root;
    }
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : 1+Math.max(getTreeDepth(root.left), getTreeDepth(root.right));
    }
    private static void printArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        //不是空树
        //root, 0, arrayWidth / 2, res, treeDepth
        if (currNode == null) {
            return;
        }
        System.out.println("行"+rowIndex);
        System.out.println("列"+columnIndex);
        //将节点存入二维数组
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);
        //res[0][arrayWidth/2]=String.valueOf(root.val)
        // 将根节点root存入res的第0排的中间
        //算层数
        int currLevel = ((rowIndex + 1) / 2);
        System.out.println("当前层级"+currLevel);
        //到最后一层返回
        if (currLevel == treeDepth) {
            return;
        }
        //计算当前行到下一行，每个元素之间的间距
        int gap = treeDepth - currLevel - 1;
        //gap=树深-当前层级-1，即 3-0-1=2，若当前层为1，则gap为3-1-1=1，为2，则3-2-1=0
        System.out.println("间隔"+gap);
        //左孩子
        if (currNode.left != null) {
            //左边不为空则在行+1，列-gap处放“/”，即root节点下一行前两格处
            res[rowIndex + 1][columnIndex-gap ] = "/";
            //继续放入当前节点的左节点进行处理，行在当前下两行，列为当前位置往前两个gap，即root下两行前四格
            printArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }
        //右孩子
        if (currNode.right != null) {
            //若有右孩子则在下一行，列加gap处放”\\“
            res[rowIndex + 1][columnIndex + gap] = "\\";
            //将右孩子放入printArray处理，位置放在下两行，列加2间隔
            printArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }
    public static void show(TreeNode root) {
        if (root == null) {
            System.out.println("null");
        }
        int treeDepth = getTreeDepth(root);//树的深度
        System.out.println("树深"+treeDepth);
        int arrayHeight = treeDepth * 2 - 1;//数组高度为深度两倍减一，考虑了数字占位和枝条占位
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        //去除根节点和0索引的占高，留下树枝的层数，因为一个节点最多俩孩子，按满二叉树计算
        // 对于一个满二叉树来说，第 n 层的节点数是 2^(n-1)。
        // 此处为最底层节点数目为2^(treeDepth - 2)，而一个节点要占三个位置，+1确保数组宽度为奇数，根节点能在正中间
        String[][] res = new String[arrayHeight][arrayWidth];//建res[][]
        //初始化数组
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = "0";//全部置空
            }
        }
        System.out.println("数组长度"+arrayWidth);
        printArray(root, 0, arrayWidth / 2, res, treeDepth);
        //将根节点，行列索引，和初始化好的空res，树深传入printArray
        //树节点和间隔与”/“”\“被存在了二维数组res中
        for (String[] line : res) {//遍历二维字符串数组res的每一行，将一行行res存入string[],将二维res对应到line里
            StringBuilder sb = new StringBuilder();//不需要创建新的字符串对象，就地修改现有对象
            for (int i = 0; i < line.length; i++) {//对于每一行都将该行的第i个元素添加到sb中
                sb.append(line[i]);//多个字符串连接起来形成一个更长的字符串
                if (line[i].length() > 1 && i <= line.length - 1) {
                    //某行第i个字符的长度大于一，且i小于等该行长度-1
                    //eg；0000-20060000，当前i为-2，当前i长2
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                    //若i字符长度大于4则跳过下一个字符，若没有则到当前字符长度减一之后的位置
                }
            }
            System.out.println(sb.toString());
        }
    }
    public static void preorder(TreeNode root, List<Integer> resultList) {
        if (root == null) return;
        resultList.add(root.val);
        preorder(root.left,resultList);
        preorder(root.right,resultList);
    }
    private static int[] preToArray(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        BinaryTree.preorder(root,resultList);
        int[] resultArray=new int[resultList.size()];
        for(int i=1;i<resultList.size();i++){
            resultArray[i]=resultList.get(i);
        }
        return resultArray;
    }
    public static int SubTreeSum(TreeNode root) {
        int sum=0;
        if (root == null) return 0;
        int[] result = BinaryTree.preToArray(root);
        for (int num : result) {
            sum+=num;
        }
        return sum;
    }
    public static TreeNode newTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.val =SubTreeSum(root);
        root.left = newTree(root.left);
        root.right = newTree(root.right);
        return root;
    }
    public static void main(String[] args) {
        int arr[]={6,7,9,0,-2,6};
        TreeNode treeNode = BinaryTree.arrayToTreeNode(arr);
        BinaryTree.show(treeNode);
        System.out.println("由该树生成的新二叉树为：");
        TreeNode newtreeNode = BinaryTree.newTree(treeNode);
        BinaryTree.show(newtreeNode);
    }
}
