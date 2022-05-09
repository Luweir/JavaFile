package Test;


import JUC.PC.A;

import java.util.*;

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0)
            return;
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i]=nums2[i];
            }
            return;
        }
        for (int i = 0; i < m; i++) {
            nums1[m + n - i - 1] = nums1[m - i - 1];
        }
        int index = 0;
        int p1 = n;
        int p2 = 0;
        while (p1 < n + m && p2 < n) {
            nums1[index++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        while (p1 < n + m)
            nums1[index++] = nums1[p1++];
        while (p2 < n)
            nums1[index++] = nums2[p2++];
    }

    public static void main(String[] args) {
        int[] test = new int[]{0};
        new Solution().merge(test, 0, new int[]{1}, 1);
        System.out.println(Arrays.toString(test));
    }
}

