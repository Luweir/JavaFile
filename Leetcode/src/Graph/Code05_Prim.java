package Graph;

import java.util.*;

public class Code05_Prim {
    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        HashSet<Node> set = new HashSet<Node>();
        HashSet<Edge> result = new HashSet<Edge>();// 依次挑选的边放result
        // 处理森林的问题：将分散的最小生成树合在一起
        for (Node node : graph.nodes.values()) {
            // node是开始点
            if (!set.contains(node)) {
                set.add(node);
                // 解锁这个点的所有的边
                priorityQueue.addAll(node.edges);
                // 有可能把一个边重复仍到队列，但不会影响最后结果，只是增加点常数时间
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();// 弹出解锁的边中 weight最小的边
                    Node toNode = edge.to; // 可能的新点
                    if (!set.contains(toNode)) { // set不含这个点 那说明是新点
                        set.add(toNode);
                        result.add(edge);
                        priorityQueue.addAll(toNode.edges);
                    }

                }
            }
        }
        return result;
    }
}
