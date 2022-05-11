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

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null)
                sb.append("#,");
            else {
                sb.append(cur.val + ",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0)
            return null;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (!nodes[index].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(nodes[index]));
                cur.left = left;
                queue.add(left);
            }
            index++;
            if (!nodes[index].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(nodes[index]));
                cur.right = right;
                queue.add(right);
            }
            index++;
        }
        return root;
    }
}

public class Solution {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n1.left = n2;
        n1.right = n3;
        n3.right = n4;
        String serialize = new Codec().serialize(n1);
        System.out.println(serialize);
    }

}

