package BinaryTree;

public class Code06_CheckFullBinaryTree {
    public boolean checkFullBinaryTree(TreeNode head) {
        if (head == null)
            return true;
        Inform data = f(head);
        return data.nodes == (1 << data.height) - 1;
    }

    public Inform f(TreeNode head) {
        if (head == null)
            return new Inform(0, 0);
        Inform leftData = f(head.left);
        Inform rightData = f(head.right);
        int height = Math.max(leftData.height, rightData.height);
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new Inform(height, nodes);
    }
}
