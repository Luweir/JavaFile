package MonotonousStack;

import java.util.Arrays;
import java.util.Stack;

public class Code01_MostRecentMax {
    // 针对无重复值版本的单调栈解决最近最大问题
    public static int[] mostRecentMax(int[] arr) {
        Stack<Integer> stack = new Stack<>(); // 放下标
        int[] res = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int index = stack.pop();
                res[index * 2] = stack.empty() ? -1 : arr[stack.peek()];
                res[index * 2 + 1] = arr[i];
            }
            stack.add(i);
        }
        while (!stack.empty()) {
            int index = stack.pop();
            res[index * 2] = stack.empty() ? -1 : arr[stack.peek()];
            res[index * 2 + 1] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 7, 2, 3, 0, 1};
        System.out.println(Arrays.toString(mostRecentMax(arr)));
    }
}
