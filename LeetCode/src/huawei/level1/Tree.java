package huawei.level1;

/**
 * 定义构造三又搜索树规则如下:
 * 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入查找的规则是:
 *
 * 1.如果数小于节点的数减去500，则将数插入节点的左子树
 *
 * 2.如果数大于节点的数加上500，则将数插入节点的右子树
 *
 * 3.否则，将数插入节点的中子树
 *
 * 给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
 *
 * 输入描述
 * 第一行为一个数N，表示有N个数，1<=N<=10000
 *
 * 第二行为N个空格分隔的整数，每个数的范围为[1,10000]
 *
 * 输出描述
 * 输出树的高度(根节点的高度为1)
 *
 * 示例一
 * 输入
 * 5
 * 5000 2000 5000 8000 1800
 * 输出
 * 3
 * 说明
 * 最终构造出的树如下，高度为3
 *
 * 输入
 * 3
 * 5000 4000 3000
 * 输出
 * 3
 * 说明
 * 最终构造出的树如下，高度为3
 *
 */
import java.util.Scanner;

public class Tree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node root = new Node(scanner.nextInt());

        for (int i = 1; i < n; i++) root.insert(scanner.nextInt());

        System.out.println(root.getHeight());
    }
}

class Node {
    public int val;
    public Node left, mid, right;

    public Node(int val) {
        this.val = val;
    }

    /**
     * 新节点插入树中
     *
     * @param nval
     */
    public void insert(int nval) {
        if (nval < this.val - 500) {    // 则将数插入节点的左子树
            if (this.left != null) this.left.insert(nval);
            else this.left = new Node(nval);
        } else if (nval > this.val + 500) { // 则将数插入节点的右子树
            if (this.right != null) this.right.insert(nval);
            else this.right = new Node(nval);
        } else {    // 将数插入节点的中子树
            if (this.mid != null) this.mid.insert(nval);
            else this.mid = new Node(nval);
        }
    }

    /**
     * 获取树的高度
     *
     * @return
     */
    public int getHeight() {
        int maxHeight = 0;
        if (this.left != null) maxHeight = Math.max(maxHeight, this.left.getHeight());
        if (this.mid != null) maxHeight = Math.max(maxHeight, this.mid.getHeight());
        if (this.mid != null) maxHeight = Math.max(maxHeight, this.mid.getHeight());
        return maxHeight + 1;
    }

}