package StringProcess;

public class Code02_Manacher {
    public static int maxLcpsLength(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = manacherString(s); // 1221 -> #1#2#2#1#
        int[] pArr = new int[str.length]; // 回文半径数组
        int R = -1; // 回文右边界的中心位置
        int C = -1; // 回文右边界的再往右一个位置，最右的有效区域是R-1位置
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) { // 每一个位置都求回文半径
            // i至少的回文区域，先给pArr[i]
            // 一、i如果在R外，那必然pArr[i]=1
            // 二、i在R内，分情况
            // 2.1 i'的回文区域全在L到R内  pArr[i]=pArr[2*C-i]
            // 2.2 i'的回文区域跨到L到R外 或者 与L重合 pArr[i]=pArr[R-i] 如果与L重合需要暴力扩
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1; // i的回文区域=i'的回文区域，
            // 上面pArr是取得两种大情况里面的最小值情况，所以还要暴力扩充
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else
                    break;
            }
            // 更新R
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        // 处理串的回文半径-1=原始串的最大回文长度
        return max - 1;
    }

    // 详细版
    public String longestPalindrome(String s) {
        // manacher
        if (s.length() == 1) return s;

        char[] chars = manacherString(s);
        int n = chars.length;
        int[] pArr = new int[n];
        int C = -1, R = -1, pos = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // 如果当前位置i不在R范围内，暴力扩
            if (i >= R) {
                pArr[i] = 1;
                while (i + pArr[i] < n && i - pArr[i] > -1) {
                    if (chars[i + pArr[i]] == chars[i - pArr[i]])
                        pArr[i]++;
                    else
                        break;
                }
            }
            // 如果i在R范围内，分情况讨论
            else {
                // i关于C对称点ii
                int ii = C * 2 - i;
                // 情况一：ii的回文区域全部在L到R范围内
                if (pArr[ii] < R - i) {
                    pArr[i] = pArr[ii];
                }
                // 情况二：ii的回文区域有部分跑到了L到R的外面，那么i的回文区域最多为RR（R关于i对称）到R
                else if (pArr[ii] > R - i) {
                    pArr[i] = R - i;
                }
                // 情况三：ii的回文区域左端点刚好等于L，那么i的回文区域至少为R-i，需要继续探索
                else {
                    pArr[i] = R - i;
                    while (i + pArr[i] < n && i - pArr[i] > -1) {
                        if (chars[i + pArr[i]] == chars[i - pArr[i]])
                            pArr[i]++;
                        else
                            break;
                    }
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (pArr[i] > max) {
                max = pArr[i];
                pos = i;
            }
        }
        int offset = pArr[pos];
        StringBuilder sb = new StringBuilder();
        for (int i = pos - offset + 1; i <= pos + offset - 1; i++) {
            if (chars[i] != '#') sb.append(chars[i]);
        }
        return sb.toString();
    }

    String getString(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    // 生成处理串数组
    private static char[] manacherString(String s) {
        char[] str = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            str[i] = (i & 1) == 0 ? '#' : s.charAt(index++);
        }
        return str;
    }

    public static void main(String[] args) {
        String s = "abcbdksgkdbcba";
        System.out.println(maxLcpsLength(s));
    }
}
