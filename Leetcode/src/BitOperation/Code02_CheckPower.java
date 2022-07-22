package BitOperation;

public class Code02_CheckPower {
    public static boolean isTwoPower1(int n) {
        return n == (n & (~n + 1));
    }

    public static boolean isTwoPower2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static boolean isFourPower(int n) {
        // 4的幂都是分布在奇数位上   0101010101010101010101010101010101010101010101010101010101010101
        return isTwoPower2(n) && (n & 0x55555555)!=0;
    }

    public static void main(String[] args) {
        System.out.println(isTwoPower1(8));
        System.out.println(isFourPower(17));
    }
}
