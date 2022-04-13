package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {
    // 从node出发，进行宽度优先遍历
    public static void bfs(Node node) {
        if (node == null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> hashSet = new HashSet<Node>(); // 防止重复放入
        queue.add(node);
        hashSet.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);// 对应操作
            for (Node next : cur.nexts) {
                if (hashSet.contains(next)) {
                    queue.add(next);
                    hashSet.add(next);
                }
            }
        }
    }
}
