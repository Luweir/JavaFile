package MyUtils;

public class MyUtils {

    //交换元素 前提 i!=j，否则就是同一内存区域自己跟自己异或，都为0
    public static void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length < 2 || i > arr.length || j > arr.length || i == j)
            return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void swap(char[] str, int i, int j) {
        if (str == null || str.length < 2 || i > str.length || j > str.length || i == j)
            return;
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
