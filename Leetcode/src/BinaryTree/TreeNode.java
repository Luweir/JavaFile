package BinaryTree;

public class TreeNode {
    // java 类属性默认保内可见，包外不可见
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
