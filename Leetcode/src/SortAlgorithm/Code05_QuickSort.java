package SortAlgorithm;

import MyUtils.MyUtils;

/**
 * 五、快速排序  时间O(NlogN)   空间好的情况选的中点O(logN)~O(N)坏的情况都在尾部
 * 能够解决带重复值的排序
 */
public class Code05_QuickSort {
    public void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            // 随机选一位数作为基准，与最后一个数进行交换
            MyUtils.swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            // p是一个两元素的数组   [基准左边界-1，基准右边界]
            int[] p = partition(arr, l, r);
            // 对基准左边的数据进行快排
            quickSort(arr, l, p[0]);
            // 对基准右边的数据进行快排
            quickSort(arr, p[1] + 1, r);
        }
    }

    // 处理arr[l..r]的函数，默认以arr[r]做划分，arr[r] -> p      <p     =p     >p
    // 返回等于区域（左边界，右边界），所以返回一个长度为2的数组res  res[0]左边界  res[1]右边界
    public int[] partition(int[] arr, int l, int r) {
        // 一个优化手段就是 如果 r-l 很小，可以用效率高的插入排序 这里不写
        // 基准是arr[r]
        int less = l - 1;//小区右边界
        int more = r;//大区左边界
        while (l < more) {
            // 当前数cur  基准base
            // 如果cur小于base，让小区右边界扩张，并让cur作为小区右边界值
            if (arr[l] < arr[r]) {
                MyUtils.swap(arr, ++less, l++);
            }
            // 如果cur大于base，让大区左边界扩张，并让cur作为大区左边界值（交换大区原左边界值和cur）
            // 之后继续判断arr[l]就行，不用l++
            else if (arr[l] > arr[r]) {
                MyUtils.swap(arr, --more, l);
            }
            // 如果cur==base，l++
            else {
                l++;
            }
        }
        // 把base与大区左边界值进行交换
        MyUtils.swap(arr, more, r);
        return new int[]{less, more};
    }
}
