package Test;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode leftGet = lowestCommonAncestor(root.left, p, q);
        TreeNode rightGet = lowestCommonAncestor(root.right, p, q);
        if (leftGet != null && rightGet != null) {
            return root;
        }
        return leftGet == null ? rightGet : leftGet;
    }

    public static void main(String[] args) {

    }
}
