package Test;


import java.util.*;
<<<<<<< HEAD

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
=======


public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        // bfs
        if (bank.length == 0)
            return -1;
        if (start.equals(end))
            return 0;
        int index = 0;
        for (; index < bank.length; index++) {
            if (bank[index].equals(end))
                break;
        }
        if (index == bank.length)
            return -1;
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int res = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            while (size-- > 0) {
                String cur = queue.poll();
                for (int i = 0; i < bank.length; i++) {
                    if (check(cur, bank[i])) {
                        if (bank[i].equals(end))
                            return res;
                        queue.add(bank[i]);
                    }
                }
            }
        }
        return -1;
    }

    public boolean check(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                count++;
            if (count > 1)
                return false;
        }
        return count == 1;
    }

    public static void main(String[] args) {
        String[] test = {"AATTCCGG", "AACCTGGG", "AACCCCGG", "AACCTACC"};
        System.out.println(new Solution().minMutation("AACCTTGG", "AATTCCGG", test));
    }
}


>>>>>>> 7b7587acd03a2ef1d3f116db6f35664a04714df7

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
