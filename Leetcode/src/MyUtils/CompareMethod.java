package MyUtils;

public class CompareMethod {
    public static int[] generateRandomIntArray(int len, int max) {
        // 不超过len的随机长度
        int[] arr = new int[(int) (Math.random() * len) + 1];
        // 不超过max的随机元素
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max + 1);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("对数开始");
        int testTime = 100; //对数次数
        for (int i = 0; i < testTime; i++) {
            // 对数
        }
    }
}
