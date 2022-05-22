package Test;


import java.util.*;

public class Solution {
    public int[] rec = new int[1 << 21];

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger>=desiredTotal)
            return true;
        if(maxChoosableInteger*(1+maxChoosableInteger)/2<desiredTotal)
            return false;
        return process(0, 0, maxChoosableInteger, desiredTotal);
    }

    public boolean process(int state, int sum, int maxChoosableInteger, int desiredTotal) {
        if (rec[state] == 1)
            return true;
        if (rec[state] == 2)
            return false;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((1 << i) & state) != 0)
                continue;
            if (sum + i >= desiredTotal) {
                rec[state] = 1;
                return true;
            }
            if (!process(((1 << i) | state), sum + i, maxChoosableInteger, desiredTotal)) {
                rec[state] = 1;
                return true;
            }
        }
        rec[state] = 2;
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canIWin(10, 11));
    }
}

