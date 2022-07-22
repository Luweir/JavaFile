package SortAlgorithm;


/**
 * 四、归并排序
 * 时间O(NlogN)      空间O(N)
 */
public class Code04_MergeSort {
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int l, int r) {
        if (l == r)
            return;
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int p1 = l, p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m)
            help[index++] = arr[p1++];
        while (p2 <= r)
            help[index++] = arr[p2++];
        System.arraycopy(help, 0, arr, l, help.length);
    }
}
