package BinaryTree;

import java.util.HashMap;
import java.util.HashSet;

public class Code08_LowestAncestor {
    public static TreeNode la1(TreeNode head, TreeNode o1, TreeNode o2) {
        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<TreeNode, TreeNode>();
        fatherMap.put(head, head);
        HashSet<TreeNode> set1 = new HashSet<TreeNode>();
        set1.add(o1);
        process(head, fatherMap);

        // 填充o1的祖先集
        TreeNode cur = o1;
        while (cur != fatherMap.get(cur)) {
            set1.add(fatherMap.get(cur));
            cur = fatherMap.get(cur);
        }
        set1.add(head);

        // 在o1里面找 看有没有o2的祖先
        cur = o2;
        while (!set1.contains(cur)) {
            cur = fatherMap.get(cur);
        }
        return cur;
    }

    // 填充Hash表
    public static void process(TreeNode head, HashMap<TreeNode, TreeNode> fatherMap) {
        if (head == null)
            return;
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);
        process(head.left, fatherMap);
        process(head.right, fatherMap);
    }

    // 递归 O(n) O(1)
    public static TreeNode la2(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        TreeNode left = la2(head.left, o1, o2);
        TreeNode right = la2(head.right, o1, o2);
        if (left != null && right != null)
            return head;
        // 左右两棵树 并不都有返回值
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n2.left = n5;
        System.out.println(la1(n1, n4, n5).value);
    }
}
