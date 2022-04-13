package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class Code10_Serialization {
    // 序列化
    public static String serialByPre(TreeNode head) {
        if (head == null)
            return "#_";
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 反序列化
    public static TreeNode reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreString(queue);
    }


    private static TreeNode reconPreString(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#"))
            return null;
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = reconPreString(queue);
        head.right = reconPreString(queue);
        return head;
    }
}
