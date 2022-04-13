package DynamicProgramm;

public class Code02_CoinCollect {

    // 暴力递归版本
    public static int recursionCC(int[] arr, int aim) {
        return f1(arr, 0, aim);
    }

    public static int f1(int[] arr, int index, int rest) {
        // 超出面值了
        if (rest < 0)
            return -1;  // -1表示无效方案
        if (rest == 0)
            return 0;
        // rest > 0
        if (index == arr.length)
            return -1; // -1表示无效方案
        // rest > 0 并且还有硬币可选  分情况
        // 注意上面无效方案-1 的返回会影响后续的Math.min()  因此需要对无效情况进行判断
        int p1 = f1(arr, index + 1, rest);
        int p2 = f1(arr, index + 1, rest - arr[index]);
        // 两种方案都行不通 那这里也只能返回-1了
        if (p1 == -1 && p2 == -1) {
            return -1;
        }
        if (p1 == -1) {
            // p1走不通走p2
            return 1 + p2;
        } else if (p2 == -1) {
            // p2走不通走p1
            return p1;
        }
        return Math.min(p1, p2 + 1);
    }

    public static int memoryRecursionCC(int[] arr, int aim) {
        // 可变参数 剩余面值 和 index要走的位置
        int[][] dp = new int[aim + 1][arr.length + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -2; // 因为-1 和 0 都有具体的意义
            }
        }
        return f2(arr, 0, aim, dp);
    }

    public static int f2(int[] arr, int index, int rest, int[][] dp) {
        // 超出面值了  同样属于缓存一种  剪枝吧
        if (rest < 0) {
            return -1;  // -1表示无效方案
        }
        // 缓存命中
        if (dp[rest][index] != -2) {
            return dp[rest][index];
        }
        if (rest == 0) {
            dp[rest][index] = 0;
        }
        // rest > 0
        else if (index == arr.length) {
            dp[rest][index] = -1;
        } else {
            // rest > 0 并且还有硬币可选  分情况
            // 注意上面无效方案-1 的返回会影响后续的Math.min()  因此需要对无效情况进行判断
            int p1 = f2(arr, index + 1, rest, dp);
            int p2 = f2(arr, index + 1, rest - arr[index], dp);
            // 两种方案都行不通 那这里也只能返回-1了
            if (p1 == -1 && p2 == -1) {
                dp[rest][index] = -1;
            } else if (p1 == -1) {
                // p1走不通走p2
                dp[rest][index] = 1 + p2;
            } else if (p2 == -1) {
                // p2走不通走p1
                dp[rest][index] = p1;
            } else {
                dp[rest][index] = Math.min(p1, p2 + 1);
            }
        }
        return dp[rest][index];
    }

    // 动态规划
    public static int CoinCollect(int[] arr, int aim) {
        int N = arr.length;
        // 初始化dp表
        int[][] dp = new int[N + 1][aim + 1];
        // 边界元素处理
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = -1;
        }

        // 表格内部元素处理
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - arr[index] >= 0)
                    p2 = dp[index + 1][rest - arr[index]];
                // 两种方案都行不通 那这里也只能返回-1了
                if (p1 == -1 && p2 == -1) {
                    dp[index][rest] = -1;
                } else if (p1 == -1) {
                    // p1走不通走p2
                    dp[index][rest] = 1 + p2;
                } else if (p2 == -1) {
                    // p2走不通走p1
                    dp[index][rest] = p1;
                } else {
                    dp[index][rest] = Math.min(p1, p2 + 1);
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 9, 10};
        System.out.println(CoinCollect(arr, 19));
    }
}
