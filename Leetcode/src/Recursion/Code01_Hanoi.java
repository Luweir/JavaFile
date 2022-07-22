package Recursion;

public class Code01_Hanoi {
    // 汉诺塔问题
    public static void hanoi(int n) {
        if (n > 0)
            move(n, "左", "右", "中");
    }

    // 把1-i个盘从 from 经过 mid 移动到 to
    private static void move(int i, String from, String to, String mid) {
        if (i == 1) {
            System.out.println("move1:" + from + "->" + to);
            return;
        }
        move(i - 1, from, mid, to);
        System.out.println("move" + i + ":" + from + "->" + to);
        move(i - 1, mid, to, from);
    }

    public static void main(String[] args) {
        hanoi(5);
    }
}
