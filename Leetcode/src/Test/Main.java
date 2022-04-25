package Test;

import java.util.*;

class Node {
    public int val;
    public Node next;

    public Node(int _val) {
        val = _val;
    }
}

public class Main {
    public int findGreaterNum(int N) {
        if (N < 10)
            return N;
        // write code here
        char[] s = ("" + N).toCharArray();
        int n = s.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty() || s[i] >= s[stack.peek()]) {
                stack.add(i);
            } else {
                int tar = stack.peek();
                while (!stack.isEmpty() && s[stack.peek()] > s[i]) {
                    tar = stack.pop();
                }
                char temp = s[i];
                s[i] = s[tar];
                s[tar] = temp;
                return Integer.parseInt(new String(s));
            }
        }
        return -1;
    }

    public static void process(String s1, String s2) {
        int p1 = 0;
        int p2 = 0;
        while (p1 < s1.length() && p2 < s2.length()) {
            if (s1.charAt(p1) == s2.charAt(p2) || s2.charAt(p2) == '.') {
                p1++;
                p2++;
                continue;
            }
            if (s2.charAt(p2) == '*') {
                if (s2.charAt(p2 - 1) == '.') {
                    System.out.println(true);
                    return;
                }
            }
            // bbbbbb b?
            if (s2.charAt(p2) == '?') {
                if (s2.charAt(p2 - 1) == '.') {
                    System.out.println(true);
                    return;
                }
                int pp1 = p1;
                while (s2.charAt(p2 - 1) == s1.charAt(pp1))
                    pp1++;
                if (pp1 == p1) {
                    System.out.println(false);
                    return;
                }
            }
            System.out.println(false);
            return;
        }
        if (p1 == s1.length() && p2 == s2.length()) {
            System.out.println(true);
            return;
        }
        if (p1 == s1.length()) {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            while (p2 < s2.length()) {
                char c = s2.charAt(p2);
                if (c == '.')
                    flag1 = true;
                if (c == '*')
                    flag2 = true;
                if (c == '?')
                    flag3 = true;
                if (c >= 'a' && c <= 'z')
                    flag4 = true;
                p2++;
            }
            if (flag4) {
                System.out.println(false);
                return;
            }
            if (flag1 && flag2) {
                System.out.println(true);
                return;
            }

            if (flag1 && !flag2 && !flag3) {
                System.out.println(false);
                return;
            }
            if (flag3 && !flag1 && !flag2) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n-- > 0) {
            String s1 = scanner.next();
            String s2 = scanner.next();
            process(s1, s2);
        }
    }
}