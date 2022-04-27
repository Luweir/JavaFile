package Test;

import JUC.PC.A;
import Link.Link;

import java.util.*;

public class Solution {
    public int[][] position = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Queue<Integer> goList = new LinkedList<>();

        List<List<Integer>> res = new LinkedList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        int mod = Math.max(m, n);
        goList.add((m - 1) * mod);
        goList.add(n - 1);

        visited[m - 1][0] = true;
        visited[0][n - 1] = true;
        List<Integer> temp = new ArrayList<>(2);
        temp.add(m - 1);
        temp.add(0);
        res.add(temp);
        temp = new ArrayList<>(2);
        temp.add(0);
        temp.add(n - 1);
        res.add(temp);
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
                        temp = new ArrayList<>(2);
                        temp.add(newX);
                        temp.add(newY);
                        res.add(temp);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] test = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        Solution main = new Solution();
        System.out.println(main.pacificAtlantic(test));
    }
}
