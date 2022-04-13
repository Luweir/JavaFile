package LeftGold100Question;

public class Code03_AddMinusWaysToSum {
    static public int process1(int[] arr, int target) {
        return f(arr, 0, target);
    }

    // 从arr[cur,....]能不能得到一种方法使其sum=target
    static public int f(int[] arr, int cur, int rest) {
        if (cur == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return f(arr, cur + 1, rest + arr[cur]) + f(arr, cur + 1, rest - arr[cur]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 7, 4, 2, 4, 5};
        System.out.println(process1(arr, 25));
    }
}
