package Test;


import java.util.*;


public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        // bfs
        if (bank.length == 0)
            return -1;
        if (start.equals(end))
            return 0;
        int index = 0;
        for (; index < bank.length; index++) {
            if (bank[index].equals(end))
                break;
        }
        if (index == bank.length)
            return -1;
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int res = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            while (size-- > 0) {
                String cur = queue.poll();
                for (int i = 0; i < bank.length; i++) {
                    if (check(cur, bank[i])) {
                        if (bank[i].equals(end))
                            return res;
                        queue.add(bank[i]);
                    }
                }
            }
        }
        return -1;
    }

    public boolean check(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                count++;
            if (count > 1)
                return false;
        }
        return count == 1;
    }

    public static void main(String[] args) {
        String[] test = {"AATTCCGG", "AACCTGGG", "AACCCCGG", "AACCTACC"};
        System.out.println(new Solution().minMutation("AACCTTGG", "AATTCCGG", test));
    }
}



