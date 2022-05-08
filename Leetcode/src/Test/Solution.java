package Test;


import java.util.*;

public class Solution {

//        // 暴力法：hashMap  o(N) o(N)
//    public List<Integer> findDuplicates(int[] nums) {
//        List<Integer> res = new LinkedList<>();
//        HashSet<Integer> hashSet = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (hashSet.contains(nums[i]))
//                res.add(nums[i]);
//            else
//                hashSet.add(nums[i]);
//        }
//        return res;
//    }
    // 利用元素 与 下标的转换
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums.length < 2)
            return res;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1 || nums[i] == 0)
                continue;
            int next = nums[i];
            nums[i] = 0;
            while (nums[next - 1] != -1 && nums[next - 1] != 0) {
                int temp = nums[next - 1];
                nums[next - 1] = -1;
                next = temp;
            }
            if (nums[next - 1] == -1)
                res.add(next);
            else
                nums[next - 1] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
