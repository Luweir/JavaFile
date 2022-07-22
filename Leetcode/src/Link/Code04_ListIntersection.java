package Link;


public class Code04_ListIntersection {
    public Node listIntersection(Node head1, Node head2) {
        if (head1 == null || head2 == null)
            return null;
        // 一、先判断有没有环
        Code03_GetLoopNode getLoopNode = new Code03_GetLoopNode();
        Node loop1 = getLoopNode.getLoopNode(head1);
        Node loop2 = getLoopNode.getLoopNode(head2);
        // 1.1 一个有环一个没环必然不成立
        if ((loop1 == null && loop2 != null) || (loop2 == null && loop1 != null)) {
            return null;
        }
        // 1.2 两个都没环 或者 二者有公共环
        Node cur1 = head1, cur2 = head2;
        if ((loop2 == loop1)) {
            int diff = 0;
            while (cur1 != null) {
                diff++;
                cur1 = cur1.next;
            }
            while (cur2 != null) {
                diff--;
                cur2 = cur2.next;
            }
            cur1 = diff > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            diff = diff > 0 ? diff : -diff;
            // 长链表先走
            while (diff > 0) {
                cur1 = cur1.next;
                --diff;
            }
            // 两个一起走
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        // 1.3 都有环 且是相插入的关系 先拿个loop1环遍历 如果环中有loop2节点  说明相交   （独立成环的情况可以在这里判断）
        else {
            cur1 = loop1.next;
            while (cur1 != loop1 && cur1 != loop2) {
                cur1 = cur1.next;
            }
            return cur1 == loop1 ? null : cur1;
        }
    }
}