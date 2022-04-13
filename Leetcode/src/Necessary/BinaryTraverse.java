package Necessary;

import BinaryTree.TreeNode;

import javax.swing.*;
import java.util.Stack;

public class BinaryTraverse {
    // 前序遍历
    public static void preOrder(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || head != null) {
            while (head != null) {
                System.out.print(head.value + " ");
                stack.add(head);
                head = head.left;
            }
            head = stack.pop();
            head = head.right;
        }
    }

    // 中序遍历
    public static void inorder(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.add(head);
                head = head.left;
            }
            head = stack.pop();
            System.out.print(head.value + " ");
            head = head.right;
        }
    }

    // 后序遍历
    public static void proOrder(TreeNode head) {
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
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        preOrder(n1);
        System.out.println();
        inorder(n1);
        System.out.println();
        proOrder(n1);
    }
}
