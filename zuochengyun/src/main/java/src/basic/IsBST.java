package src.basic;

import src.class08.Code03_IsBST;

import java.util.ArrayList;

public class IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //由于递归的返回值有3个，所有要构造一个类接受这3个返回值
    public static class ReturnType {
        public boolean isBST;//是否是搜索二叉树
        public int min;
        public int max;

        public ReturnType(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    public static ReturnType proccess(Node head){
        if (head == null) {
            return null;
        }
        ReturnType leftInfo = proccess(head.left);
        ReturnType rightInfo = proccess(head.right);
        int min = head.value;
        int max = head.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean isBST = false;
        if (
                (leftInfo == null ? true : (leftInfo.isBST && leftInfo.max < head.value))
                        &&
                        (rightInfo == null ? true : (rightInfo.isBST && rightInfo.min > head.value))
        ) {
            isBST = true;
        }
        return new ReturnType(isBST,min,max);
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static boolean isBST2(Node head){
        if (head == null) {
            return true;
        }
        return proccess(head).isBST;
    }
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
