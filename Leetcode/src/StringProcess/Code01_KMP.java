package StringProcess;

public class Code01_KMP {
    public static int kmp(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length())
            return -1;
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2); // O(M)
        // O(N)
        while (i1 < str1.length && i2 < str2.length) {
            // s=abbstkscabbstks   m=abbstkfgabbstkz
            // a=a 都往右走
            if (str1[i1] == str2[i2]) {
                ++i1;
                ++i2;
            } else if (next[i2] == -1) {
                // 如果往前跳跳到了m的第一个元素，且s[i1]不等于它，就直接i1右走
                ++i1;
            } else {
                // s[14]!=m[14] i2跳到next[14] =>i2=6 然后循环 比较s[14]和m[6]
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    // 生成next数组！！！  next[i]表示从 0到i-1的最长前缀=后缀
    private static int[] getNextArray(char[] str2) {
        if (str2.length == 1)
            return new int[]{-1};
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // next数组起始位置
        int cn = 0; // 很奇妙 他很重要 跟记录前后缀有关
        while (i < next.length) {
            // str[1]与str[cn=0]相等 next[2]=cn+1
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                // 当前跳到cn位置的字符和i-1位置的字符配不上
                cn = next[cn];
            } else {
                // 两个既不相等 且 cn==0 说明没跳的地方
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "abbstkgcabbstks";
        String m = "abbstks";
        System.out.println(kmp(s, m));
    }
}
