package Link;

import java.util.HashMap;
import java.util.Stack;

public class Link {
    public static void isPalindrome1Test(Node head) {
        System.out.println((new Code01_IsPalindrome()).isPalindrome1(head));
        System.out.println((new Code01_IsPalindrome()).isPalindrome2(head));
        System.out.println((new Code01_IsPalindrome()).isPalindrome3(head));
    }

    public static void getLoopNode(Node head) {
        System.out.println(new Code03_GetLoopNode().getLoopNode(head).value);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(2);
        Node n5 = new Node(5);
        Node n6 = new Node(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n3;
//        isPalindrome1Test(n1);
        getLoopNode(n1);
    }
}
