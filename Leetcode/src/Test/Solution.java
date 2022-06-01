package Test;


import java.util.*;

public class Solution {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4)
            return false;
        int sum = 0;
        for (int i = 0; i < matchsticks.length; i++) {
            sum += matchsticks[i];
        }
        if (sum % 4 != 0)
            return false;
        return dfs(0, matchsticks, sum / 4, sum / 4, sum / 4, sum / 4);
    }

    private boolean dfs(int i, int[] matchsticks, int i1, int i2, int i3, int i4) {
        if (i == matchsticks.length)
            return true;
        if (matchsticks[i] <= i1 && dfs(i + 1, matchsticks, i1 - matchsticks[i], i2, i3, i4))
            return true;
        if (matchsticks[i] <= i2 && dfs(i + 1, matchsticks, i1, i2 - matchsticks[i], i3, i4))
            return true;
        if (matchsticks[i] <= i3 && dfs(i + 1, matchsticks, i1, i2, i3 - matchsticks[i], i4))
            return true;
        if (matchsticks[i] <= i4 && dfs(i + 1, matchsticks, i1, i2, i3, i4 - matchsticks[i]))
            return true;
        return false;
    }


    public static void main(String[] args) {
    }
}

