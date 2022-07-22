package BinaryTree;

/**
 * 递归前序、中序、后序遍历
 */
public class Code01_RecursiveTraversal {
    public void preOrderTraversal(TreeNode head) {
        if (head == null)
            return;
        System.out.print(head.value + " ");
        preOrderTraversal(head.left);
        preOrderTraversal(head.right);
    }

    public void inOrderTraversal(TreeNode head) {
        if (head == null)
            return;
        inOrderTraversal(head.left);
        System.out.print(head.value + " ");
        inOrderTraversal(head.right);
    }

    public void proOrderTraversal(TreeNode head) {
        if (head == null)
            return;
        proOrderTraversal(head.left);
        proOrderTraversal(head.right);
        System.out.print(head.value + " ");
    }
}
