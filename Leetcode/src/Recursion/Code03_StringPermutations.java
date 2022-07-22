package Recursion;

import MyUtils.MyUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 不重复的字符串全排列
 */
public class Code03_StringPermutations {
    // str[0...i-1]的位置我都固定了，现在解决 str[i...]的位置交换
    public static void process(char[] str, int i, List<String> list) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            list.add(String.valueOf(str));
            return;
        }
        boolean[] record = new boolean[26];
        for (int j = i; j < str.length; j++) {
            // 去重，虽然可以全部加入再统一去重，但没有这种剪枝来的快，它降低了常数项时间；   整体复杂度没变
            if (!record[str[j] - 'a']) {
                record[str[j] - 'a'] = true;
                MyUtils.swap(str, i, j);
                process(str, i + 1, list);
                MyUtils.swap(str, i, j);
            }
        }
    }

    public static void main(String[] args) {
        String str = "abcde";
        List<String> list = new ArrayList<>();
        process(str.toCharArray(), 0, list);
    }
}
