package Test;


import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class MyLinkedList {
    public ListNode head;
    public ListNode tail;
    public int size;

    public MyLinkedList() {
        this.head = new ListNode(-1);
        this.tail = head;
        this.size = 0;
    }

    public int get(int index) {
        if (index >= size)
            return -1;
        ListNode cur = head.next;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = this.head.next;
        this.head.next = node;
        this.size++;
        if (node.next == null)
            tail = node;
    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        this.tail.next = node;
        this.tail = node;
        this.size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size)
            return;
        else if (index == size)
            addAtTail(val);
        else {
            ListNode cur = head;
            while (index-- > 0)
                cur = cur.next;
            ListNode node = new ListNode(val);
            node.next = cur.next;
            cur.next = node;
            size++;
            if (node.next == null)
                tail = node;
        }

    }

    public void deleteAtIndex(int index) {
        if (index >= size)
            return;
        ListNode cur = head;
        while (index-- > 0)
            cur = cur.next;
        cur.next = cur.next.next;
        size--;
        if (cur.next == null)
            tail = cur;

    }
}

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {

    }

}

