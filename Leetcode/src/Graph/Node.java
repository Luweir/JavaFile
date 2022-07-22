package Graph;

import java.util.ArrayList;

public class Node {
    public int value; // 节点值/编号
    public int in; // 入度
    public int out; // 出度
    public ArrayList<Node> nexts; // 从当前点出发 直接连接的点（直接邻居）
    public ArrayList<Edge> edges; // 属于当前节点的边  由它出发的边 的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
