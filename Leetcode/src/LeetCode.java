import java.util.*;


public class LeetCode {
    public int findTargetSumWays(int[] nums, int target) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        return process(nums, 0, target, hashMap);
    }

    public int process(int[] nums, int cur, int rest, HashMap<String, Integer> hashMap) {
        String s = cur + "_" + rest;
        if (hashMap.containsKey(s))
            return hashMap.get(s);
        if (cur == nums.length) {
            if (rest == 0) {
                hashMap.put(s, 1);
                return 1;
            }
            hashMap.put(s, 0);
            return 0;
        }

        int r1 = process(nums, cur + 1, rest - nums[cur], hashMap);
        int r2 = process(nums, cur + 1, rest + nums[cur], hashMap);
        hashMap.put(s, r1 + r2);
        return r1 + r2;

    }

    public static void main(String[] args) {
    }
}
