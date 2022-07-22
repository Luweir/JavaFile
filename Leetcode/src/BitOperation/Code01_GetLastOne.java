package BitOperation;

public class Code01_GetLastOne {
    public static int getLastOne(int n) {
        return n & (~n + 1);
    }

    public static void main(String[] args) {
        System.out.println(getLastOne(27));
    }
}
