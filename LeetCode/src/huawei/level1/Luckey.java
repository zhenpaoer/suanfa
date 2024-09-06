package huawei.level1;

import java.util.Arrays;

/**
 * 给一个正整数数列 nums，一个跳数 jump，及幸存数量 left。
 * 运算过程为：从索引0的位置开始向后跳，中间跳过 J 个数字，命中索引为 J+1 的数字，该数被敲出，并从该点起跳，以此类推，直到幸存 left 个数为止，然后返回幸存数之和。
 *
 * 约束条件：
 * 1. 0是第一个起跳点;
 * 2. 起跳点和命中点之间间隔 jump 个数字，已被敲出的数字不计入在内;
 * 3. 跳到末尾时无缝从头开始（循环查找），并可以多次循环;
 * 4. 若起始时 left > len(nums) 则无需跳数处理过程;
 * 5. 方法设计：
 *
 *
 *  * @param nums 正整数数列，长度范围 [1, 10000]
 *  * @param jump 跳数，范围 [1, 10000]
 *  * @param left 幸存数量，范围 [0, 10000]
 *  * @return 幸存数之和
 *
 * int sumOfLeft(int[] nums, int jump, int left)
 * 输入描述：
 * 1. 第一行输入正整数数列;
 * 2. 第二行输入跳数;
 * 3. 第三行输入幸存数量;
 *
 * 输出描述:
 * 输出幸存数之和
 *
 * 示例1：
 * 输入
 * 1, 2, 3, 4, 5, 6, 7, 8, 9
 * 4
 * 3
 *
 * 输出
 * 13
 * 说明：从1(索引0)开始起跳，中间跳过4个数字，因此依次删除6, 2, 8, 5, 4, 7。剩余1, 3, 9返回和为13；
 */
import java.util.Arrays;
        import java.util.Scanner;

public class Luckey {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个正整数列nums：");
        String[] numstr = sc.nextLine().split(" ");
        System.out.println(Arrays.toString(numstr));//
        int[] nums = new int[numstr.length];

        for (int i = 0; i < numstr.length; i++) {
            nums[i] = Integer.parseInt(numstr[i]);
        }
        System.out.println(Arrays.toString(nums));//
        System.out.println("请输入一个跳数jump：");
        int jump = sc.nextInt();
        System.out.println("请输入幸存数量left：");
        int left = sc.nextInt();
        /*
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int jump = 4;
        int left = 3;
        System.out.println("原始数组：" + Arrays.toString(nums));
         */
        int sum = sumOfLeft(nums, jump, left);
        System.out.println("幸存数之和为：" + sum);
    }

    private static int sumOfLeft(int[] nums, int jump, int left) {
        if (nums.length <= left) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            return sum;
        } else {
            // 初始化循环链表
            CircularLinkedList.Node head = new CircularLinkedList.Node(nums[0]);
            CircularLinkedList.Node current = head;

            // 创建循环链表
            for (int i = 1; i < nums.length; i++) {
                current.next = new CircularLinkedList.Node(nums[i]);
                current = current.next;
            }
            current.next = head; // 完成循环

            // 删除节点
            CircularLinkedList.Node currentNode = head;
            CircularLinkedList.Node prevNode = null;
            while (getLength(head) > left) {
                if (getLength(head)==9){
                    for (int i = 0; i < jump+1; i++) {
                        prevNode = currentNode;
                        System.out.println("1当前i值为："+i+",节点值为"+currentNode.data);
                        currentNode = currentNode.next;
                        System.out.println("2当前i值为："+i+",节点值为"+currentNode.data);
                    }
                }else {
                    for (int i = 0; i < jump; i++) {
                        prevNode = currentNode;
                        System.out.println("1当前i值为："+i+",节点值为"+currentNode.data);
                        currentNode = currentNode.next;
                        System.out.println("2当前i值为："+i+",节点值为"+currentNode.data);
                    }
                }
                // 删除当前节点
                System.out.println("3当前节点值为"+currentNode.data);
                deleteNode(head, prevNode, currentNode);
                currentNode = prevNode.next; // 更新currentNode到下一个节点
                System.out.println("当前链表长"+getLength(head));
            }

            System.out.println("幸存数为：");
            for (int i = 0; i < left; i++){
                System.out.println(currentNode.data);//
                currentNode = currentNode.next;
            }

            // 计算幸存节点的和
            int sum = 0;
            currentNode = head;
            do {
                sum += currentNode.data;
                currentNode = currentNode.next;
            } while (currentNode != head);

            return sum;
        }
    }

    private static void deleteNode(CircularLinkedList.Node head, CircularLinkedList.Node prevNode, CircularLinkedList.Node currentNode) {
        if (currentNode == null || head == null) {
            return;
        }

        // 删除链表的唯一节点
        if (currentNode == head && currentNode.next == head) {
            head = null;
            return;
        }

        if (currentNode == head) {
            head = head.next;
        }

        prevNode.next = currentNode.next;

        // 如果删除的是头节点，需要更新头节点
        if (currentNode == head) {
            head = currentNode.next;
        }
    }

    private static int getLength(CircularLinkedList.Node head) {
        if (head == null) return 0;
        int length = 0;
        CircularLinkedList.Node current = head;
        do {
            length++;
            current = current.next;
        } while (current != head);
        return length;
    }

    static class CircularLinkedList {
        static class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }
    }
}