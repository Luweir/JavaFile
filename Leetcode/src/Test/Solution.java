package Test;

import JUC.PC.A;
import Link.Link;

import java.util.*;

public class Solution {
    public int[][] position = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Queue<Integer> goList = new LinkedList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited1 = new boolean[m][n];
        int mod = Math.max(m, n) + 1;

        // 标记所有能通往太平洋的点
        for (int i = 0; i < m; i++) {
            goList.add(i * mod);
            visited1[i][0] = true;
        }
        for (int i = 0; i < n; i++) {
            goList.add(i);
            visited1[0][i] = true;
        }
        process(heights, goList, mod, visited1);
        boolean[][] visited2 = new boolean[m][n];
        goList.clear();
        // 标记所有能通往大西洋的点
        for (int i = 0; i < m; i++) {
            goList.add(i * mod + n - 1);
            visited2[i][n - 1] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            goList.add((m - 1) * mod + i);
            visited2[m - 1][i] = true;
        }

        process(heights, goList, mod, visited2);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited1[i][j] && visited2[i][j]) {
                    List<Integer> temp = new ArrayList<>(2);
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                }
            }
        }
        return res;
    }

    public void process(int[][] heights, Queue<Integer> goList, int mod, boolean[][] visited) {
        int m = heights.length;
        int n = heights[0].length;
        while (!goList.isEmpty()) {
            int size = goList.size();
            while (size-- > 0) {
                int cur = goList.poll();
                int x = cur / mod;
                int y = cur % mod;
                for (int i = 0; i < 4; i++) {
                    int newX = x + position[i][0];
                    int newY = y + position[i][1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && heights[newX][newY] >= heights[x][y] && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        goList.add(newX * mod + newY);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] test = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        Solution main = new Solution();
        System.out.println(main.pacificAtlantic(test));
    }
}
