package Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_IPO {
    public static class Node {
        public int c;// 本金
        public int p;// 利润

        Node(int cc, int pp) {
            c = cc;
            p = pp;
        }
    }

    //最小本金小根堆
    public static class MinCapitalComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    // 最大利润大根堆
    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int IPO(int K, int W, int[] profits, int[] capitals) {
        PriorityQueue<Node> pq1 = new PriorityQueue<>(new MinCapitalComparator());
        PriorityQueue<Node> pq2 = new PriorityQueue<>(new MaxProfitComparator());

        for (int i = 0; i < profits.length; i++) {
            pq1.add(new Node(capitals[i], profits[i]));
        }

        // 进行K轮 （最多串行做K个项目）
        for (int i = 0; i < K; i++) {
            // 小根堆的项目至少有一个能做的,就把能做的都放入大根堆
            while (!pq1.isEmpty() && pq1.peek().c <= W) {
                pq2.add(pq1.poll());
            }
            if (pq2.isEmpty())
                return W;
            W += pq2.poll().p;
        }
        return W;
    }
}
