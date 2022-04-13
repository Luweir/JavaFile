package Necessary;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2)
            return;
        process(array, 0, array.length - 1);
    }

    public static void process(int[] array, int l, int r) {
        if (l == r)
            return;
        int mid = l + ((r - l) >> 1);
        process(array, l, mid);
        process(array, mid + 1, r);
        merge(array, l, mid, r);
    }

    public static void merge(int[] array, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int p1 = l, p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[index++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }
        while (p1 <= m)
            help[index++] = array[p1++];
        while (p2 <= r)
            help[index++] = array[p2++];
        System.arraycopy(help, 0, array, l, help.length);
    }

    public static void main(String[] args) {
        int[] test = {1, 5, 23, 324, 4, 56, 456546, 234, 2, 121, 31, 5, 4, 3, 23, 6, 234, 2, 7};
        int[] test2 = Arrays.copyOf(test, test.length);
        mergeSort(test);
        Arrays.sort(test2);
    }
}
