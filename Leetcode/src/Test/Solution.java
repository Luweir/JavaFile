package Test;

import Link.Link;

import java.util.*;

public class Solution {
    public static long res = 0;

    // rest 还有的钱   keep 持股数
    public static long process(HashMap<String, Long> hashMap, int[] a, int cur, long rest, long keep) {
        if (cur == a.length - 1) {
            return rest + keep * a[cur];
        }
        String s = cur + "_" + rest + "_" + keep;
        if (hashMap.containsKey(s))
            return hashMap.get(s);
        long res = -1;
        if (rest - a[cur] >= 0) {
            res = Math.max(process(hashMap, a, cur + 1, rest - a[cur], keep + 1), res);
        }
        res = Math.max(process(hashMap, a, cur + 1, rest + a[cur], keep - 1), res);
        hashMap.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        HashMap<String, Long> hashMap = new HashMap<>();
        process(hashMap, a, 0, m, 0);
        System.out.println(res);
    }
}
