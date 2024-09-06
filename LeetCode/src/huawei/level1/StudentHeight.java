package huawei.level1;

import java.util.Stack;

/**
 * 在学校中，N个小朋友站成一队， 第i个小朋友的身高为height[i]， </div> <div>
 *     第i个小朋友可以看到的第一个比自己身高更高的小朋友j，那么j是i的好朋友(要求j > i)。
 * </div> <div>  请重新生成一个列表，对应位置的输出是每个小朋友的好朋友位置，如果没有看到好朋友，请在该位置用0代替。
 * </div> <div>  小朋友人数范围是 [0, 40000]。
 * 输入
 * 第一行输入N，表示有N个小朋友
 * 第二行输入N个小朋友的身高height[i]，都是整数
 *
 * 输出
 * 输出N个小朋友的好朋友的位置
 */
public class StudentHeight {
    public static int[] findFriends(int[] heights) {
        int n = heights.length;
        int[] friends = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i=n-1;i>=0;i--){
            int t = heights[i];
            while (!stack.empty()&&t>=heights[stack.peek()]){
                stack.pop();
            }
            if (!stack.empty()){
                friends[i]=stack.peek();
            }
            stack.push(i);
        }

        return friends;
    }
    public static int[] findFriends1(int[] heights) {
        int n = heights.length;
        int[] friends = new int[n];

        //8 4 2 3 5
        for (int i = 0; i < n; i++) {
            friends[i]=0;
            for (int j=i+1;j<n;j++){
                //判断以后的每一个元素是否存在一个比当前元素大
                if (heights[j]>heights[i]){
                    friends[i]=j;
                    //找到了 结束循环
                    break;
                }
            }

        }

        return friends;
    }
}
