package BinaryTree;

public class Code09_SubsequentNodes {
    // 存在指向父节点的属性
    class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode father; // 根节点的father=null

        TreeNode(int v) {
            value = v;
        }
    }

    // 方法一：中序遍历找后继
    // 方法二：列举后继节点的情况讨论
    // 1）如果X有右树，x的后继=右树上的最左节点；
    // 2）x无右树，我是不是我父亲的左孩子，如果是 返回父亲，如果不是 继续往上走，我父亲是不是我爷爷的左孩子…
    public static TreeNode sn(TreeNode head, TreeNode node) {
        if (node.right != null) {
            TreeNode cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        while (node.father != null && node.father.left != node) {
            node = node.father;
        }
        return node.father;
    }
}
