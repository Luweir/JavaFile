package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    public static void traversalTest(TreeNode head) {
        Code01_RecursiveTraversal recursiveTraversal = new Code01_RecursiveTraversal();
        Code02_NonRecursiveTraversal nonRecursiveTraversal = new Code02_NonRecursiveTraversal();
        System.out.println("递归前序遍历");
        recursiveTraversal.preOrderTraversal(head);
        System.out.println("\n递归中序遍历");
        recursiveTraversal.inOrderTraversal(head);
        System.out.println("\n递归后序遍历");
        recursiveTraversal.proOrderTraversal(head);
        System.out.println("\n栈+头右左=非递归前序遍历");
        nonRecursiveTraversal.preOrderTraversal(head);
        System.out.println("栈+左右头=非递归中序遍历");
        nonRecursiveTraversal.inOrderTraversal(head);
        System.out.println("双栈+头右左=非递归后序遍历");
        nonRecursiveTraversal.proOrderTraversal(head);
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
//        n3.left = n6;
        n3.right = n7;
//        traversalTest(n1);
        System.out.println(new Code04_CheckCompleteBinaryTree().checkCompleteBinaryTree(n1));
    }
}
