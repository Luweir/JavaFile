package Recursion;

public class Code08_NQueens {
    public static int num1(int n) {
        if (n < 1)
            return 0;
        // record[i]=k  表示第i行的皇后放在第k列
        int[] record = new int[n];
        return process1(0, record, n);
    }


    // 处理第i行的皇后  返回合法的摆法
    // 任何皇后不同行 不同列 不共斜线
    public static int process1(int i, int[] record, int n) {
        // 边界处理  到n时已经ok
        if (i == n)
            return 1;
        int res = 0;
        // 当前行始终在i行，但尝试所有的列
        for (int j = 0; j < n; j++) {
            // 第i行第j列的皇后时候能放
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    // 判断record[0....i-1]是否与第i行的皇后冲突
    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(k - i) == Math.abs((record[k] - j)))
                return false;
        }
        return true;
    }

    // -------------------------------------------------------
    // 使用位运算加速，比较难
    public static int num2(int n) {
        if (n < 1 || n > 32)
            return 0;
        int limit = n == 32 ? -1 : (1 << n) - 1;// n皇后就让limit的最后n位为1，其他所有位为0
        return process2(limit, 0, 0, 0);
    }

    // colLim列的限制，1的位置不能放皇后，0的位置可以放
    // leftDiaLim左斜线限制，1的位置不能放，0的位置可以放
    // rightDiaLim右斜线限制，1的位置不能放，0的位置可以放
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (limit == colLim) // 所有皇后都填上了 base case
            return 1;
        // pos表示所有候选皇后的位置    1表示可以放的位置，0表示不可以放的位置
        // (colLim | leftDiaLim | rightDiaLim) 总限制，再取反 => 1可以放 0不可以放
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1); // 最右侧为1的位置  准备放皇后
            pos = pos - mostRightOne; // 相当于pos这个位置置0
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;
        long start = System.currentTimeMillis();
        System.out.println(num1(n));
        long end = System.currentTimeMillis();
        System.out.print("耗时 ");
        System.out.println(end - start);

        start = System.currentTimeMillis();
        System.out.println(num2(n));
        end = System.currentTimeMillis();
        System.out.print("耗时 ");
        System.out.print(end - start);
    }
}
