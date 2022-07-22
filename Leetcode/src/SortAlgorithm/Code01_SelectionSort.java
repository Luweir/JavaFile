package SortAlgorithm;

import MyUtils.MyUtils;

/**
 * 一、选择排序
 * 时间O(N2)  空间O(1)
 */
public class Code01_SelectionSort {
    public void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            MyUtils.swap(arr, i, minIndex);
        }
    }
}
