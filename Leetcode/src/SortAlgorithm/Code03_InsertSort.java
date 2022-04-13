package SortAlgorithm;

import MyUtils.MyUtils;

// 三、插入排序   时间O(N) ~ O(N^2)     空间O(1)

public class Code03_InsertSort {

    public void insertSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        // 0~i-1有序，想0~i有序
        for (int i = 1; i < arr.length; ++i) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; --j) {
                MyUtils.swap(arr, j, j + 1);
            }
        }
    }
}
