package SortAlgorithm;

/**
 * 4.1 小和问题
 */
public class Code04_SmallSum {
    public int res = 0;

    public int smallSum(int[] arr) {
        process(arr, 0, arr.length - 1);
        return res;
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
            // 想等的话一定要先拷贝右组
            if (arr[p1] < arr[p2]) {
                help[index++] = arr[p1];
                res += (r - p2 + 1) * arr[p1];
                p1++;
            } else {
                help[index++] = arr[p2++];
            }
        }
        while (p1 <= m)
            help[index++] = arr[p1++];
        while (p2 <= r)
            help[index++] = arr[p2++];
        System.arraycopy(help, 0, arr, l, help.length);
    }
}

