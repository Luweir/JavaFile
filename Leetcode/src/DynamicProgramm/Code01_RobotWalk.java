package DynamicProgramm;

public class Code01_RobotWalk {
    // 暴力递归版本
    public static int recursionRW(int N, int S, int E, int K) {
        return f1(N, E, K, S);
    }

    public static int f1(int n, int e, int rest, int cur) {
        // 剩余步数为0  如果到终点则返回1 说明这是一种有效方案
        if (rest == 0)
            return cur == e ? 1 : 0;
        // 走到左边界 只能往右走了
        if (cur == 1) {
            return f1(n, e, rest - 1, 2);
        }
        // 走到右边界 只能往左走了
        if (cur == n) {
            return f1(n, e, rest - 1, n - 1);
        }
        // 中间位置情况 左右都可以走
        return f1(n, e, rest - 1, cur - 1) + f1(n, e, rest - 1, cur + 1);
    }

    // 递归+记忆化搜索
    public static int memoryRecursionRW(int N, int S, int E, int K) {
        // 针对两个可变参数：剩余步数rest的范围和当前位置cur的范围进行缓存数据设置
        int[][] dp = new int[K + 1][N + 1];
        // 初始化
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(N, E, K, S, dp);
    }

    public static int f2(int n, int e, int rest, int cur, int[][] dp) {
        // 先看看缓存表有没有
        if (dp[rest][cur] != -1) {
            return dp[rest][cur];
        }
        // 如果缓存区没有再往下一步走  得到的值要放入表的格子中
        // 剩余步数为0  如果到终点则返回1 说明这是一种有效方案
        if (rest == 0) {
            dp[rest][cur] = cur == e ? 1 : 0;
            return dp[rest][cur];
        }
        // 走到左边界 只能往右走了
        if (cur == 1) {
            dp[rest][cur] = f2(n, e, rest - 1, 2, dp);
        } else if (cur == n) {
            // 走到右边界 只能往左走了
            dp[rest][cur] = f2(n, e, rest - 1, n - 1, dp);
        } else {
            // 中间位置情况 左右都可以走
            dp[rest][cur] = f2(n, e, rest - 1, cur - 1, dp) + f2(n, e, rest - 1, cur + 1, dp);
        }
        return dp[rest][cur];
    }

    // 严格表格动态规划
    public static int robotWalk(int N, int S, int E, int K) {
        // 参考记忆化缓存表格
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }
        dp[0][E] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else if (j == N) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[4][2];
    }

    public static void main(String[] args) {
        System.out.println(robotWalk(5, 2, 4, 4));
    }
}
