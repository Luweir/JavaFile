package SortAlgorithm;

import MyUtils.MyUtils;

import java.util.Arrays;

public class Code06_HeapSort {
    // 六、堆排序       时间复杂度O(NlogN)    空间复杂度O(1)
    // 大根堆实现升序，小根堆实现降序
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        // 各个元素插入
        for (int i = 0; i < arr.length; i++) { // O(logN)
            heapInsert(arr, i); // O(logN)
        }
        int heapSize = arr.length;
        // 交换堆顶节点和堆末节点，并堆化直到heapSize=0
        MyUtils.swap(arr, 0, --heapSize);
        while (heapSize > 0) { // O(logN)
            heapify(arr, 0, heapSize); // O(logN)
            MyUtils.swap(arr, 0, --heapSize); // O(1)
        }

    }

    // 某个数处于index位置，往上继续移动，由下至上
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            MyUtils.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //堆化：在取出堆顶节点后进行操作，由上至下
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;// index节点的左孩子
        while (left < heapSize) {// 当index还有孩子时
            // 如果右孩子存在并且右孩子值大于左孩子，则最大值为右孩子值，否则为左孩子值
            int largest = left + 1 <= heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父节点和孩子最大值比较，孩子大则交换
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            MyUtils.swap(arr, largest, index);
            // 对交换了节点的位置进行下一步heapify
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 5, 2, 2, 1, 1, 3, 1, 1, 8, 1, 2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
