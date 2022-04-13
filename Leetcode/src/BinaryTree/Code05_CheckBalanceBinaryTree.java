package BinaryTree;

/**
 * 判断是否为平衡二叉树；
 * 需要①两个子树均为平衡二叉树 ②两个子树的高度差不超过1
 */
public class Code05_CheckBalanceBinaryTree {
    public boolean checkBalanceBinaryTree(TreeNode head) {
        if (head == null)
            return true;
        Inform ret = f(head);
        return ret.isBalanced;
    }

    public Inform f(TreeNode head) {
        if (head == null)
            return new Inform(true, 0);
        Inform leftData = f(head.left);
        Inform rightData = f(head.right);
        int height = Math.max(leftData.height, rightData.height);
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
        return new Inform(isBalanced, height);
    }
}