package Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public int res = 0;

    public boolean isFile(String s) {
        for (int i = s.length() - 1; i >= 1; i--) {
            if (s.charAt(i) == '.')
                return true;
        }
        return false;
    }

    public void process(String[] inputs, int s, int e, int layer, int len) {
        if (s > e)
            return;
        for (int i = s; i <= e; i++) {
            if (isFile(inputs[i]))
                res = Math.max(res, len + inputs[i].length() - layer + 1);
            else {
                int j = i + 1;
                for (; j <= e + 1; j++) {
                    if (j == e + 1 || inputs[j].lastIndexOf('\t') + 1 == layer) {
                        process(inputs, i + 1, j - 1, layer + 1, len + inputs[i].length() - layer + (layer == 0 ? 0 : 1));
                        break;
                    }
                }
                i = j - 1;
            }
        }
    }

    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0)
            return 0;
        String[] inputs = input.split("\n");
        int n = inputs.length;
        // 第一个是文件 或者全程没有子目录  那么文件和第一级目录都在第一层
        if (isFile(inputs[0]) || input.lastIndexOf('\t') == -1) {
            for (int i = 0; i < n; i++) {
                if (isFile(inputs[i]))
                    res = Math.max(inputs[i].length(), res);
            }
        } else {
            process(inputs, 0, n - 1, 0, 0);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthLongestPath("a\n\tb\n\t\tc.txt\n\taaaa.txt"));
    }
}
