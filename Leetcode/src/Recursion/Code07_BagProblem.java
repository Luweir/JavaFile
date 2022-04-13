package Recursion;

public class Code07_BagProblem {
    // 当前处理第i个背包，只需要考虑 arr[i...]后面的情况，认为arr[0...i-1]已经定了
    // alreadyWeight  当前背包物品总量
    public static int process(int[] weights, int[] values, int i, int alreadyWeight, int bag) {
        if (i == weights.length)
            return 0;
        // 加入当前物品超重 => 只能不加入当前物品
        if (alreadyWeight + weights[i] > bag)
            return process(weights, values, i + 1, alreadyWeight, bag);
        // 加入当前物品未超重，就要看是加好还是不加好
        return Math.max(process(weights, values, i + 1, alreadyWeight, bag), values[i] + process(weights, values, i + 1, alreadyWeight + weights[i], bag));
    }
}
