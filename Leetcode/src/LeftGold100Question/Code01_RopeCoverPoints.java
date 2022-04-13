package LeftGold100Question;

public class Code01_RopeCoverPoints {
    public static int process(int[] arr, int k) {
        int left = 0;
        int right = 0;
        int res = 1;
        while (++right < arr.length) {
            while (arr[right] - arr[left] > k) {
                left++;
            }
            res = Math.max(right - left + 1, res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 7, 13, 16, 17, 18, 19, 20};
        System.out.println(process(arr, 7));
    }
}
