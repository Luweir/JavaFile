package Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public void recoverTree(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> nodeList = new LinkedList<>();
        // 得到中序遍历
        while (!stack.isEmpty() || root != null) {
            if (root != null ) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            nodeList.add(root);
            root = root.right;
        }
        int p1 = 0, p2 = nodeList.size() - 1;
        while (p1 < p2 && nodeList.get(p1).val <= nodeList.get(p1 + 1).val)
            p1++;
        while (p1 < p2 && nodeList.get(p2).val >= nodeList.get(p2 - 1).val)
            p2--;
        int temp = nodeList.get(p1).val;
        nodeList.get(p1).val = nodeList.get(p2).val;
        nodeList.get(p2).val = temp;
    }
}
