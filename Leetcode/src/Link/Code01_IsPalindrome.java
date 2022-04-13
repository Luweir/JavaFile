package Link;

import java.util.Stack;

/**
 * 一、判断是否为回文链表
 */
public class Code01_IsPalindrome {
    // 利用栈实现：O(N)时间复杂度+O(N)空间复杂度
    public boolean isPalindrome1(Node head) {
        if (head == null || head.next == null)
            return true;
        Stack<Integer> stack = new Stack<>();
        Node start = head;
        while (start != null) {
            stack.add(start.value);
            start = start.next;
        }
        start = head;
        while (!stack.empty()) {
            if (stack.pop() != start.value) {
                return false;
            }
            start = start.next;
        }
        return true;
    }

    // 利用快慢指针+栈实现：O(N) 、  O(N/2)
    public boolean isPalindrome2(Node head) {
        if (head == null || head.next == null)
            return true;
        Node n1 = head;
        Node n2 = head;
        // 快慢指针找到中点
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        // 后半部分放入栈中
        Stack<Integer> stack = new Stack<>();
        while (n2 != null) {
            stack.add(n2.value);
            n2 = n2.next;
        }
        while (!stack.empty()) {
            if (stack.pop() != head.value)
                return false;
            head = head.next;
        }
        return true;
    }

    // 利用快慢指针+链表翻转实现：O(N)时间复杂度+O(1)空间复杂度
    public boolean isPalindrome3(Node head) {
        if (head == null || head.next == null)
            return true;
        Node n1 = head;
        Node n2 = head;
        // 快慢指针找到中点
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // 此时n1指向中点，n2指向尾部
        n2 = n1.next;
        n1.next = null;
        // 后半部分翻转
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1; // n3->save last node
        n2 = head;
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 复原翻转链表
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

}