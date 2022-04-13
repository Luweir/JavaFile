package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否为完全二叉树
 */
public class Code04_CheckCompleteBinaryTree {
    public boolean checkCompleteBinaryTree(TreeNode head) {
        if (head == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean leaf = false;
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // 经历过孩子不双全的节点后仍遇到非叶子结点 或者 右孩子有左孩子没有的节点  return false
            if ((leaf && (cur.left != null || cur.right != null)) || (cur.right != null && cur.left == null))
                return false;
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
            else {
                leaf = true;
            }
        }
        return true;
    }
}