package BitOperation;

public class Code03_AddMinusMultiDivideByBits {
    // 加法操作
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b; // 无进位加法
            b = (a & b) << 1; // 进位信息
            a = sum;
        }
        return sum;
    }

    // 得到a的相反数   如果a+b 本来就会溢出 用户是sb
    public static int getNeg(int a) {
        return add(~a, 1);
    }

    // 减法操作
    public static int minus(int a, int b) {
        return add(a, getNeg(b));
    }

    // 乘法操作 如果a*b本来就会溢出 用户是sb
    public static int multi(int a, int b) {
        int sum = 0;
        while (b != 0) {
            sum += (b & 1) == 1 ? a : 0;
            a = a << 1;
            b = b >>> 1;
        }
        return sum;
    }

    public static boolean isNeg(int a) {
        return ((a >> 31) & 1) == 1;
    }

    // 除法操作  b!=0
    public static int divide(int a, int b) {
        // 负数取正
        int x = isNeg(a) ? getNeg(a) : a;
        int y = isNeg(b) ? getNeg(b) : b;
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((x >> i) >= y) {
                x = minus(x, y << i);
                res |= (1 << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? getNeg(res) : res;
    }

    public static void main(String[] args) {
        System.out.println(divide(-56, 3));
    }

}
