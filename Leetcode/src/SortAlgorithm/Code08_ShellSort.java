package SortAlgorithm;

public class Code08_ShellSort {
    // 希尔排序
    public void shellSort(int[] nums) {
        int len = nums.length;
        int gap = len / 2;  // 数据被分为gap组，即组内两个元素相距gap
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int cur = nums[i];
                int preIndex = i - gap;
                // 在组内已被排序过数据中倒叙找到合适位置，
                while (preIndex >= 0 && nums[preIndex] > cur) {
                    nums[preIndex + gap] = nums[preIndex];
                    preIndex -= gap;
                }
                nums[preIndex + gap] = cur;
            }
            gap /= 2;
        }
    }
}
