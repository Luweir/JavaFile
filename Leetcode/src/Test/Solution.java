package Test;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length <= 1)
            return logs;
        PriorityQueue<String> priorityQueue = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int p1 = 0, p2 = 0;
                while (s1.charAt(p1) != ' ')
                    p1++;
                while (s2.charAt(p2) != ' ')
                    p2++;
                p1++;
                p2++;
                int flag = s1.substring(p1).compareTo(s2.substring(p2));
                return flag == 0 ? s1.substring(0, p1).compareTo(s2.substring(0, p2)) : flag;
            }
        });
        int index = logs.length - 1;
        for (int i = logs.length - 1; i >= 0; i--) {
            int p1 = 0;
            while (logs[i].charAt(p1) != ' ')
                p1++;
            p1++;
            char c = logs[i].charAt(p1);
            if (c >= 'a' && c <= 'z') {
                priorityQueue.add(logs[i]);
            } else {
                logs[index--] = logs[i];
            }
        }
        for (int i = 0; i < logs.length && !priorityQueue.isEmpty(); i++) {
            logs[i] = priorityQueue.poll();
        }
        return logs;
    }

    public static void main(String[] args) {
        String[] s = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        System.out.println(Arrays.toString(new Solution().reorderLogFiles(s)));
    }
}
