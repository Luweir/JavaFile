package Test;

import java.util.*;

<<<<<<< HEAD
public class Test{

=======
public class Test {
    public String res = "";
    public int maxCount = 0;

    public void check(HashMap<String, Integer> stringCount, String s) {
        int curCount = stringCount.getOrDefault(s, 0);
        if (curCount + 1 > maxCount) {
            maxCount = curCount + 1;
            res = s;
        }
        stringCount.put(s, curCount + 1);
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0)
            return res;
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList(banned));
        HashMap<String, Integer> stringCount = new HashMap<>();
        int p1 = 0, p2 = 0;
        while (p2 < paragraph.length()) {
            char c = paragraph.charAt(p2);
            if (c == ',' || c == ' ' || c == '!' || c == '?' || c == ':' || c == '.') {
                String cur = paragraph.substring(p1, p2);
                cur = cur.toLowerCase();
                if (!hashSet.contains(cur)) {

                    check(stringCount, cur);
                }
                p2 += c == ' ' ? 0 : 1;
                p1 = p2 + 1;
            }
            p2++;
        }
        return res;
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
    }
>>>>>>> ed65429f590d921c6cf96e1cdceda1b71ec8e70a
}