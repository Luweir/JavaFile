package Necessary;

import JUC.PC.A;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    // 经典款
    public static void quickSort1(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int basic = arr[r];
        int p1 = l, p2 = r;
        while (p1 < p2) {
            while (p1 < p2 && arr[p1] <= basic)
                p1++;
            if (p1 < p2)
                arr[p2--] = arr[p1];
            while (p1 < p2 && arr[p2] >= basic)
                p2--;
            if (p1 < p2)
                arr[p1++] = arr[p2];
        }
        arr[p1] = basic;
        quickSort1(arr, l, p1 - 1);
        quickSort1(arr, p1 + 1, r);
    }

    // 荷兰彩旗版
    public static void quickSort2(int[] arr, int l, int r) {
        if (l >= r)
            return;
        swap(arr, new Random().nextInt(r - l + 1) + l, r);
        int[] p = partition(arr, l, r);
        quickSort2(arr, l, p[0] - 1);
        quickSort2(arr, p[1] + 1, r);
    }

    private static int[] partition(int[] arr, int l, int r) {
        int basic = arr[r];
        int less = l - 1;// 小区右边界
        int more = r + 1;// 大区左边界
        while (l < more) {
            if (arr[l] > basic) {
                swap(arr, --more, l);
            } else if (arr[l] < basic) {
                // 注意这里一定要让l++，因为l与less互换元素后，l位置一定是等于basic的  不需要再比较
                swap(arr, ++less, l++);
            } else {
                l++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
