package Test;

import java.util.*;


class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}


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
//    /**
//     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
//     *
//     * @param a ListNode类一维数组 指向每段碎片的开头
//     * @return ListNode类
//     */
//    public ListNode solve(ListNode[] a) {
//        // 1）形成环 O(n)
//        int n = a.length;
//        HashMap<Integer, ListNode> nodeHashSet = new HashMap<>();
//        int minNode = Integer.MAX_VALUE;
//        for (int i = 0; i < n; i++) {
//            ListNode cur = null;
//            if (!nodeHashSet.containsKey(a[i].val)) {
//                if (a[i].val < minNode)
//                    minNode = a[i].val;
//                nodeHashSet.put(a[i].val, new ListNode(a[i].val));
//            }
//            cur = nodeHashSet.get(a[i].val);
//            ListNode arrayNext = a[i].next;
//            while (arrayNext != null) {
//                ListNode next = null;
//                if (!nodeHashSet.containsKey(arrayNext.val)) {
//                    if (arrayNext.val < minNode)
//                        minNode = arrayNext.val;
//                    next = new ListNode(arrayNext.val);
//                    nodeHashSet.put(arrayNext.val, next);
//                } else {
//                    next = nodeHashSet.get(arrayNext.val);
//                }
//                cur.next = next;
//                cur = next;
//                arrayNext = arrayNext.next;
//            }
//        }
//        // 2）切开字典序最小
//        ListNode head = nodeHashSet.get(minNode);
//        int[] arr = new int[nodeHashSet.size() - 1];
//        ListNode cur = head.next;
//        int index = 0;
//        ListNode pre = head;
//        while (cur.val != head.val) {
//            arr[index++] = cur.val;
//            pre = cur;
//            cur = cur.next;
//        }
//        pre.next = null;
//        int p1 = 0, p2 = arr.length - 1;
//        while (arr[p1] == arr[p2]) {
//            p1++;
//            p2--;
//        }
//        // 顺时针
//        if (arr[p1] < arr[p2]) {
//            return head;
//        }
//        // 逆时针
//        cur = head.next;
//        while (cur != null) {
//            cur.val = arr[--index];
//            cur = cur.next;
//        }
//        return head;
//    }
//
//    public static void main(String[] args) {
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(2);
//        ListNode l5 = new ListNode(3);
//        ListNode l6 = new ListNode(4);
//        ListNode l7 = new ListNode(4);
//        ListNode l8 = new ListNode(1);
//        l1.next = l2;
//        l2.next = l3;
//        l3.next = null;
//        l4.next = l5;
//        l5.next = l6;
//        l6.next = null;
//        l7.next = l8;
//        l8.next = null;
//        Solution solution = new Solution();
//        System.out.println(solution.solve(new ListNode[]{l1, l4, l7}));
//    }
}
