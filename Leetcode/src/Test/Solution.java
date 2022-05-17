package Test;


import java.util.*;

public class Solution {
    static class Inner {
        public int[] a = new int[100];

        public void print() {
            System.out.println(a.length);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Inner inner1 = new Inner();
        Inner inner2 = new Inner();
        System.out.println(inner1.a == inner2.a);
    }

}

