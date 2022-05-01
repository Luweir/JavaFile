package Test;


import java.util.*;


public class Solution {
    public void solution(int N) {
        int enable_print = N % 10;
        while (N > 0) {
            if (enable_print == 0) {
                enable_print = (N / 10) % 10;
            } else if (enable_print != 0) {
                System.out.print(N % 10);
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
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(11110000);
    }
}
