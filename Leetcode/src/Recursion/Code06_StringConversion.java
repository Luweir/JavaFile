package Recursion;

public class Code06_StringConversion {
    public static int stringConversion(char[] str) {
        if (str == null || str.length == 0)
            return 0;
        return process(str, 0);
    }

    // 处理str[i...]子问题    str[0 ... i-1]已经搞定
    public static int process(char[] str, int i) {
        if (i == str.length)
            return 1;
        if (str[i] == '0')
            return 0;
        if (str[i] == '1') {
            int res = process(str, i + 1);
            if (i + 1 < str.length)
                res += process(str, i + 2);
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && str[i + 1] >= '1' && str[i] <= '6') {
                res += process(str, i + 2);
            }
            return res;
        }
        return process(str, i + 1);
    }
}
