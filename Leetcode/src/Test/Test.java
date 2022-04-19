package Test;

import java.util.HashSet;
import java.util.Scanner;

class Main {
    public static long process(int[] coins, int cur, int tar) {
        if (coins[cur - 1] == 0 && coins[cur + 1] == 0) {
            if (cur == tar)
                return coins[cur]+1;
            else
                return Integer.MIN_VALUE;
        }

        long turnLeft = Integer.MIN_VALUE;
        long turnRight = Integer.MIN_VALUE;
        if (cur > 0 && cur < coins.length - 1) {
            if (coins[cur - 1] > 0) {
                coins[cur - 1] -= 1;
                turnLeft = process(coins, cur - 1, tar);
                coins[cur - 1] += 1;
            }
            if (coins[cur + 1] > 0) {
                coins[cur + 1] -= 1;
                turnRight = process(coins, cur + 1, tar);
                coins[cur + 1] += 1;
            }
        }
        if (turnLeft == Integer.MIN_VALUE && turnRight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        if (turnLeft != Integer.MIN_VALUE && turnRight != Integer.MIN_VALUE)
            return Math.max(turnLeft, turnRight) + coins[cur];
        return (turnLeft == Integer.MIN_VALUE ? turnRight : turnLeft) + coins[cur];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] coins = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            coins[i] = scanner.nextInt();
        }
        if (coins[k] == 0)
            System.out.println(0);
        else {
            long res = process(coins, k, k);
            System.out.println(res);
        }

    }
//    public static int p = 0;

//    public static long process(int[] nums, HashSet<Integer> visit, int cur) {
//        if (visit.size() == nums.length)
//            return 0;
//        long res = Integer.MAX_VALUE;
//        for (int i = 1; i < nums.length; i++) {
//            if (i == cur || visit.contains(i))
//                continue;
//            else {
//                visit.add(i);
//                // 移到任意的
//                res = Math.min(process(nums, visit, i) + (Math.abs(i - cur) == 1 ? Math.max(0, nums[i] - nums[cur]) : p), res);
//                visit.remove(i);
//            }
//        }
//        return res;
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int k = scanner.nextInt();
//        p = k;
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = scanner.nextInt();
//        }
//        HashSet<Integer> visit = new HashSet<>();
//        visit.add(0);
//        long res = process(nums, visit, 0);
//        System.out.println(res);
//    }
}
