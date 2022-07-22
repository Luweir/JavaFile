package Graph;

import java.util.*;

/**
 * 拓扑排序
 */
public class Code03_TopologySort {
    public static List<Node> topologySort(Graph graph) {
        // key：某一个node
        // value： 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
        // 入度为0的点，才能进入这个队列
        Queue<Node> zeroQueue = new LinkedList<Node>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0)
                zeroQueue.add(node);
        }
        // 拓扑排序结果，依次加入result
        List<Node> result = new ArrayList<Node>();
        while (!zeroQueue.isEmpty()) {
            Node cur = zeroQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0)
                    zeroQueue.add(next);
            }
        }
        return result;
    }
}
