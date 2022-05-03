package Test;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] time = new int[n][2];
        for (int i = 0; i < n; i++) {
            time[i][0] = scanner.nextInt();
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < n; i++) {
            time[i][1] = scanner.nextInt();
            priorityQueue.add(time[i]);
        }
        int res = 0;
        int start = Integer.MIN_VALUE;
        int end = Integer.MIN_VALUE;
        while (!priorityQueue.isEmpty()) {
            int[] cur = priorityQueue.poll();
            if (cur[0] > end) {
                res++;
                start = cur[0];
                end = cur[1];
            } else {
                end = Math.min(end, cur[1]);
            }
        }
        System.out.println(res);
    }
}

