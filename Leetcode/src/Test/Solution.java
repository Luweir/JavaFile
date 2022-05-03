package Test;


import java.util.*;

<<<<<<< HEAD
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
=======

public class Solution {
    public void solution(int N) {
        int enable_print = N % 10;
        while (N > 0) {
            if (enable_print == 0) {
                enable_print = (N / 10) % 10;
            } else if (enable_print != 0) {
                System.out.print(N % 10);
>>>>>>> 08553653c1069b274559fd3aa8a1c0a1c810a63c
            }
            N = N / 10;
        }
    }
    public int solution(String S) {
        // 优先在左右都有房子的地方 放水
        char[] chars = S.toCharArray();
        int n = chars.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && i < n - 1 && chars[i] == '-' && chars[i - 1] == 'H' && chars[i + 1] == 'H') {
                chars[i - 1] = 'N';
                chars[i] = 'N';
                chars[i + 1] = 'N';
                res++;
            }
        }
        // 对于现有房屋  有空就加水
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'N' || chars[i] == '-')
                continue;
            // 如果左边能加
            if (i > 0 && chars[i - 1] == '-' && chars[i] == 'H') {
                chars[i - 1] = 'N';
                chars[i] = 'N';
                res++;
            }
            // 左边不行再考虑右边
            else if (i < n - 1 && chars[i + 1] == '-' && chars[i] == 'H') {
                chars[i] = 'N';
                chars[i + 1] = 'N';
                res++;
            } else {
                return -1;
            }
        }
        // 检查是否还有房子没水
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'H')
                return -1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(11110000);
    }
}
