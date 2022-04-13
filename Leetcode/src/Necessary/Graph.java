package Necessary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    // 邻接矩阵->图  N*3的矩阵 [weight,from,to]
    public Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            Integer weight = matrix[i][0];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(from, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.next.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}

class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> next;
    public ArrayList<Edge> edges;

    public Node(int value, int in, int out) {
        this.value = value;
        this.in = in;
        this.out = out;
        this.next = new ArrayList<>();
        this.edges = new ArrayList<Edge>();
    }

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
    }
}

class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}