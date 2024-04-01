package src.suanfa.stack;

import java.util.Stack;

/**
 * 实现⼀个这样的栈，这个栈除了可以进⾏普通的push、pop操作以外，还可以进⾏getMin的操
 * 作，getMin⽅法被调⽤后，会返回当前栈的最⼩值。栈⾥⾯存放的都是 int 整数，并且数值的范
 * 围是 [-100000, 100000]。要求所有操作的时间复杂度是 O(1)。 附加：如果空间复杂度也能O(1)
 * 的话可加分。
 *
 * ，例如 arr
 * = {2, 1, 3, 0}，那么把这些元素⼊栈时，stack 栈中元素以及最⼩值的变化如下
 *
 * 入栈元素   入栈后元素  最小值
 * 2        0（初始化0）  2
 * 1        -1          1
 * 3        2           1
 * 0        -1          0
 */
public class MinStack {
    Stack<Integer> stack = new Stack<>();
    int min;

    public void push(Integer data){
        if (stack.isEmpty()) {
            stack.push(0);
            min = data;
        }else {
            int compare = data - min;
            stack.push(compare);
            // 如果差值⼩于0，显然 data 成为最⼩值，否则最⼩值不变
            min = compare < 0 ? data : compare;
        }
    }

    public void pop(){
        Integer top = stack.peek();
        // 如果top⼩于0，显然最⼩值也⼀并会被删除，此时更新最⼩值
        min = top < 0 ? (min - top):min;
        stack.pop();
    }

    public Integer getMin(){
        return min;
    }
}
