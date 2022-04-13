package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class Code03_BFS {
    public void bfs(TreeNode head) {
        if (head == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }
}
