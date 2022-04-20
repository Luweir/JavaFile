package Test;

import java.util.*;

public class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() == 0)
            return 0;
        int[][] cache = new int[s.length()][t.length()];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
        return process(cache, s, 0, t, 0);
    }

    public int process(int[][] cache, String s, int cur, String t, int index) {
        if (index == t.length()) {
            return 1;
        }
        if (cache[cur][index] != -1)
            return cache[cur][index];
        int res = 0;
        for (int i = cur; i < s.length(); i++) {
            if (index < t.length() && s.charAt(i) == t.charAt(index)) {
                res += process(cache, s, i + 1, t, index + 1);
            }
        }
        cache[cur][index] = res;
        return res;
    }
}
