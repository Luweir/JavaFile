package LeftGold100Question;

public class Code02_GAndBSwap {
    static public int process(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        int left = 0;
        while (chars[left] != 'B')
            ++left;
        int index = left + 1;
        while (index < chars.length) {
            while (index < chars.length && chars[index] != 'G') {
                ++index;
            }
            if (index>=chars.length)
                return res;
            res += (index - left);
            ++index;
            ++left;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "GGGGBBGGGG";
        System.out.println(process(s));
    }
}
