package Necessary;

import java.util.HashMap;
import java.util.HashSet;

public class LRUCache {
    // 双向循环链表存<key value>
    // HashMap存<key Node>
    public static class Node {
        public int key;
        public int value;
        public Node next, pre;

        public Node(int key, int value, Node next, Node pre) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }
    }

    public HashMap<Integer, Node> hashMap;
    public Node head;
    public int capacity; // 规定容量

    public LRUCache(int capacity) {
        hashMap = new HashMap<>();
        head = new Node(-1, -1, null, null);
        this.capacity = capacity;
    }

    public int get(int key) {
        int res = -1;
        if (hashMap.containsKey(key)) {
            Node cur = hashMap.get(key);
            // 删除cur原先位置
            cur.next.pre = cur.pre;
            cur.pre.next = cur.next;
            // 在head后插入cur
            head.next.pre = cur;
            cur.next = head.next;
            head.next = cur;
            cur.pre = head;
            res = cur.value;
        }
        return res;
    }

    public void put(int key, int value) {
        Node cur = null;
        // 如果已存在cur节点  更新值   删除原先位置
        if (hashMap.containsKey(key)) {
            cur = hashMap.get(key);
            cur.value = value;
            cur.next.pre = cur.pre;
            cur.pre.next = cur.next;
        } else {
            // 如果不存在
            // 如果容量已满  删除链表最后一个节点
            if (hashMap.size() == capacity) {
                Node del = head.pre;
                head.pre = del.pre;
                del.pre.next = head;
                hashMap.remove(del.key);
            }
            // 创建新的cur节点
            cur = new Node(key, value, null, null);
        }
        // 在链表头部插入
        if (head.next == null) {
            head.next = cur;
            cur.next = head;
            cur.pre = head;
            head.pre = cur;
        } else {
            head.next.pre = cur;
            cur.next = head.next;
            head.next = cur;
            cur.pre = head;
        }
        hashMap.put(key, cur);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        System.out.println(1);
    }
}
