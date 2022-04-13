package Recursion;

import java.util.Stack;

public class Code05_StackReverse {
    // 栈的逆序
    public static void stackReverse(Stack<Integer> stack) {
        if (stack == null || stack.empty())
            return;
        int stackBottom = f(stack);
        stackReverse(stack);
        stack.add(stackBottom);
    }

    // 抛出栈底元素而不改变栈中其他元素
    private static int f(Stack<Integer> stack) {
        int cur = stack.pop();
        if (stack.empty()) {
            return cur;
        } else {
            int ret = f(stack);
            stack.add(cur);
            return ret;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(3);
        stack.add(2);
        stack.add(1);
        stackReverse(stack);
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
