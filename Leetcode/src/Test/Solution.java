package Test;


import java.util.*;


public class Solution {
<<<<<<< HEAD
    
=======
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashSet<Character> hashSet = new HashSet<>();
        int p1 = 0, p2 = 0;
        int res = 0;
        while (p2 < s.length()) {
            char c = s.charAt(p2);
            if (hashSet.contains(c)) {
                res = Math.max(p2 - p1, res);
                while (hashSet.contains(c)) {
                    hashSet.remove(s.charAt(p1));
                    p1++;
                }
                hashSet.add(c);
            } else {
                hashSet.add(c);
            }
            p2++;
        }
        res = Math.max(p2 - p1, res);
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("bbb"));
    }
>>>>>>> b98750d7f5493b30fcf6b6ed3fea1c7a7f37410e
}

