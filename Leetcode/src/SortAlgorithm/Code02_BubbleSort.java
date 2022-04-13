package SortAlgorithm;

import MyUtils.MyUtils;

/**
 * 二、冒泡排序
 * 时间O(N2) 空间O(1)
 */
public class Code02_BubbleSort {
    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = arr.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1])
                    MyUtils.swap(arr, j, j + 1);
            }
        }
    }
}
