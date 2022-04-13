package SortAlgorithm;


/**
 * 七、基数排序   适用于非负数  空间复杂度O(N)
 */
public class Code07_RadixSort {
    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    /**
     * 求arr数组中 最大数字对应的十进制位
     *
     * @param arr
     * @return
     */
    public int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    /**
     * 对arr数组 l 到 r的数据进行排序，最大数字的十进制为为digit位
     *
     * @param arr
     * @param l
     * @param r
     * @param digit
     */
    public void radixSort(int[] arr, int l, int r, int digit) {
        final int radix = 10;// 以10为基底，不变的
        int i = 0, j = 0;
        int[] bucket = new int[r - l + 1]; // 辅助空间
        for (int d = 1; d <= digit; d++) {
            // 10个空间，count[i]当前位（d位）是0-i的数字有多少个
            int[] count = new int[radix];
            for (int m = l; m < r; m++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (int k = 1; k < radix; k++) {
                count[k] = count[k - 1] + count[k];
            }
            for (int k = r; k >= l; k--) {
                j = getDigit(arr[k], d);
                bucket[count[j] - 1] = arr[k];
                count[j]--;
            }
            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = bucket[j];
            }
        }

    }

    public int getDigit(int num, int d) {
        int res = 0;
        while (d != 0) {
            res = num % 10;
            num /= 10;
            d--;
        }
        return res;
    }
}

