package BinaryTree;

public class Inform {
    public boolean isBalanced;
    public int height;
    public int nodes;

    Inform(boolean isBalanced, int h) {
        this.isBalanced = isBalanced;
        this.height = h;
    }

    Inform(int height, int nodes) {
        this.height = height;
        this.nodes = nodes;
    }
}
