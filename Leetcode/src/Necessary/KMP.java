package Necessary;

public class KMP {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            }
            // 如果next[i2]==-1   说明i2处于m的起始位置   i2=0
            else if (next[i2] == -1) {
                i1++;
            }
            // 如果当前字符没匹配上 且 i2!=-1  那么就跳到next[i2]位置  继续比
            else {
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0; // cn表示当前字符 的前面字符串所能匹配的最长前缀后缀长度
        while (i < next.length) {
            // next[i] = next[i-1] +1  相当于 next[i]=cn+1
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            }
            // 如果不等   看还能不能继续往前面跳
            else if (cn > 0) {
                cn = next[cn];
            }
            // 不等且跳不动了  那就最长前缀后缀长度为0  此时cn=0
            else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));

    }
}
