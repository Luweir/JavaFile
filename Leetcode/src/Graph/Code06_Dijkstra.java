package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 单源最短路径：dijkstra
 */
public class Code06_Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node head) {
        // 从head出发到所有点的最小距离
        // key：从head出发到key   value：从head出发到key的最小距离
        // 如果在表中没有T的记录  则表示head到T目前的距离为无穷大
        HashMap<Node, Integer> distanceMap = new HashMap<Node, Integer>();
        distanceMap.put(head, 0);
        // 已经求过距离的节点  存在selectedNodes里面  再也不用
        HashSet<Node> selectedNodes = new HashSet<Node>();
        // 获得distanceMap中最短的且没有锁定的节点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                // 如果head到toNode距离为无穷大
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                }
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        // 这里是顺序遍历   实际上可以优化堆   堆中某个值突然变化需要向上或向下调整  需要自己实现  如果用java的堆那就是全局扫描效率低
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
