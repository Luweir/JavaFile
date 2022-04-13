package SlidingWindow;

import Link.Link;

import java.util.*;

public class Code01_MaximumWindow {
    public static int[] maxWindow(int[] arr, int w) {
        Deque<Integer> queue = new LinkedList<>();
        int[] result = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 如果arr[i] 大于 队尾元素，要放进去
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);

            // 限制窗口最左边的下标不小于 i-w   控制有的没的元素个数为w个
            if (queue.peekFirst() == i - w) {
                queue.pollFirst();
            }
            // 已有窗口，即 i>w 就会形成窗口
            if (i >= w - 1) {
                result[index++] = arr[queue.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = maxWindow(arr, 3);
        System.out.println(Arrays.toString(res));
    }
}
