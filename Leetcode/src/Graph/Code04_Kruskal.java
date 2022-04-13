package Graph;

import java.util.*;

public class Code04_Kruskal {
    // 简洁版并查集  并查集还是这两个功能：并和查  但更快常数级别
    public static class MySets {
        public HashMap<Node, List<Node>> setMap;

        MySets(List<Node> nodes) {
            // 初始时所有节点自身即为一个集合
            for (Node node : nodes) {
                List<Node> set = new ArrayList<Node>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        // 判断环是否存在：即from和to是否在一个集合
        public boolean isSameSet(Node from, Node to) {
            return setMap.get(from) == setMap.get(to);
        }

        // 把from和to所在的集合合并
        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    // kruskal算法
    public static Set<Edge> kruskalMST(Graph graph) {
        List<Node> list = new ArrayList<Node>(graph.nodes.values());
        MySets unionFind = new MySets(list);
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        priorityQueue.addAll(graph.edges);
        Set<Edge> result = new HashSet<Edge>();
        while (!priorityQueue.isEmpty()) { // M条边
            Edge edge = priorityQueue.poll(); // O(logM)
            if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }


}
