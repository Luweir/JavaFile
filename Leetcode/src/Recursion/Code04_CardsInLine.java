package Recursion;

public class Code04_CardsInLine {
    public static int cardsInLine(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        // f(arr, 0, arr.length - 1)A先手 A得到的分数
        // s(arr, 0, arr.length - 1)A先手 B得到的分数
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    // 先手拿什么
    public static int f(int[] arr, int i, int j) {
        if (i == j)
            return arr[i];
        // arr[i] + s(arr, i + 1, j) A拿最前面的+B操作
        // arr[j] + s(arr, i, j - 1) A拿最后面的+B操作
        // 两种可能取最大值 = A想赢
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    // 给先手留下什么
    public static int s(int[] arr, int i, int j) {
        if (i == j)
            return 0;
        // 给A留下i+1和j的选择   => B拿走i；
        // 给A留下i和j-1的选择   => B拿走j；
        // 两种可能取最小值 => B不想A赢，给A留下最小的
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }
}
