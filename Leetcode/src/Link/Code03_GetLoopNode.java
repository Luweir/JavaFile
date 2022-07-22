package Link;

public class Code03_GetLoopNode {
    public Node getLoopNode(Node head) {
        if (head == null || head.next == null)
            return null;
        Node quick = head.next.next, slow = head.next;
        boolean isLoop = false;
        while (quick != null) {
            if (quick == slow) {
                isLoop = true;
                break;
            }
            quick = quick.next == null ? null : quick.next.next;
            slow = slow.next;
        }
        if (!isLoop) {
            return null;
        }
        quick = head;
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;
        }
        return quick;
    }
}
