package Graph;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {
    public static void dfs(Node node) {
        if (node == null)
            return;
        Stack<Node> stack = new Stack<Node>();
        HashSet<Node> hashSet = new HashSet<Node>();
        stack.add(node);
        hashSet.add(node);
        System.out.println(node); // 在放进去之前进行操作
        while (!stack.empty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!hashSet.contains(next)) {
                    stack.push(cur); // 精髓
                    stack.push(next); // push其实就是调用了Vector的addElement方法  add返回布尔类型 而push则返回插入元素的类型
                    hashSet.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
