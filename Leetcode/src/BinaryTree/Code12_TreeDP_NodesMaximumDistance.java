package BinaryTree;

public class Code12_TreeDP_NodesMaximumDistance {
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        int height;
        int maxDistance;

        Info(int dis, int h) {
            height = h;
            maxDistance = dis;
        }
    }

    public static int maxDistance(Node head) {
        return process(head).maxDistance;
    }

    public static Info process(Node x) {
        // base case
        if (x == null)
            return new Info(0, 0);

        // 拿到信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // 处理信息
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(Math.max(p1, p2), p3);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }
}
