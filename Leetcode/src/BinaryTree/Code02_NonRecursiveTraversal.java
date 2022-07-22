package BinaryTree;

import java.util.Stack;

/**
 * 二、非递归前序、中序、后序遍历
 */
public class Code02_NonRecursiveTraversal {
    // 栈+头右左
    public void preOrderTraversal(TreeNode head) {
        if (head == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                System.out.println(head.value);
                stack.push(head);
                head = head.left;
            }
            head = stack.pop();
            head = head.right;
        }
    }

    // 双栈+头左右
    public void proOrderTraversal(TreeNode head) {
        if (head == null)
            return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(head);
        while (!stack1.empty()) {
            TreeNode cur = stack1.pop();
            stack2.add(cur);
            if (cur.left != null)
                stack1.add(cur.left);
            if (cur.right != null)
                stack1.add(cur.right);
        }
        while (!stack2.empty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    // 栈+左右头
    public void inOrderTraversal(TreeNode head) {
        if (head == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || head != null) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            head = stack.pop();
            System.out.println(head.value);
            head = head.right;
        }
    }
}
