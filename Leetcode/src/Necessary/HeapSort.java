package Necessary;

import MyUtils.MyUtils;

import java.util.Arrays;

import static SortAlgorithm.Code06_HeapSort.heapify;

public class HeapSort {
    // 大根堆
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        // 每个元素执行堆插入操作
        for (int i = 0; i < arr.length; i++)
            heapInsert(arr, i);
        int heapSize = arr.length;
        MyUtils.swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            MyUtils.swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            MyUtils.swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            MyUtils.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
