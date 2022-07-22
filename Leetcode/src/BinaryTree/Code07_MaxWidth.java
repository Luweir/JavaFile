package BinaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// 计算一棵二叉树的最大宽度（各层节点数的最大值）
public class Code07_MaxWidth {
    public static int maxWidth(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        HashMap<TreeNode, Integer> layerMap = new HashMap<TreeNode, Integer>();
        int curLayer = 1;
        int curLayerNodes = 0;
        int result = -1;
        queue.add(head);
        layerMap.put(head, 1);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (layerMap.get(curNode) == curLayer) {
                curLayerNodes++;
            } else {
                result = Math.max(result, curLayerNodes);
                curLayer++;
                curLayerNodes = 1;
            }
            if (curNode.left != null) {
                queue.add(curNode.left);
                layerMap.put(curNode.left, curLayer + 1);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                layerMap.put(curNode.right, curLayer + 1);
            }
        }
        return result;
    }
}
