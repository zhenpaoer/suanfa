package src.basic;

import src.class08.Code01_IsBalanced;

public class IsBalanceTree {
    public static class Node {
        public int value;
        public  Node left;
        public  Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    // 左、右要求一样，Info 信息返回的结构体
    public static class Info {
        public boolean isBalaced;
        public int height;

        public Info(boolean b, int h) {
            isBalaced = b;
            height = h;
        }
    }

    public static Info proccess(Node head ){
        if (head == null){
            return new Info(true,0);
        }
        Info left = proccess(head.left);
        Info right = proccess(head.right);
        int height = Math.max(left.height, right.height) +1;
        boolean isBalance = true;
        if (!left.isBalaced || !right.isBalaced || Math.abs(left.height- right.height)  > 1){
            isBalance = false;
        }
        return new Info(isBalance,height);
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != proccess(head).isBalaced) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
