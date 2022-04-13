package Link;

import java.util.HashMap;

public class Code02_CopyListWithRand {
    public Node copyListWithRand1(Node head) {
        if (head == null)
            return null;
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != head) {
            // cur 老    map.get(cur) 新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public Node copyListWithRand2(Node head) {
        if (head == null)
            return null;
        Node cur = head;
        Node nextNode = null;
        // 复制各个节点加塞后源节点后面
        while (cur != null) {
            nextNode = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = nextNode;
            cur = nextNode;
        }
        cur = head;
        // 复制rand指针
        while (cur != null) {
            cur.next.rand = cur.rand == null ? null : cur.rand.next;
            cur = cur.next.next;
        }
        // 分离
        Node res = head.next;
        cur = head;
        while (cur != null) {
            nextNode = cur.next.next;
            cur.next.next = nextNode == null ? null : nextNode.next;
            cur.next = nextNode;
            cur = nextNode;
        }
        return res;
    }
}
