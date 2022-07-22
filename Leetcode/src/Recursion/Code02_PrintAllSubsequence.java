package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code02_PrintAllSubsequence {
    // 优化版本
    public static void process2(char[] str, int i) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            return;
        }
        process2(str, i + 1);
        char temp = str[i];
        str[i] = 0;
        process2(str, i + 1);
        str[i] = temp;
    }

    // 常规版本
    public static void process1(char[] str, int i, List<Character> list) {
        if (i == str.length) {
            printString(list);
            return;
        }
        process1(str, i + 1, list);
        List<Character> listKeep = copyList(list);
        listKeep.add(str[i]);
        process1(str, i + 1, listKeep);
    }

    private static List<Character> copyList(List<Character> list) {
        List<Character> ret = new ArrayList<>(list);
        return ret;
    }

    private static void printString(List<Character> list) {
        System.out.println(list.toString());
    }

    public static void main(String[] args) {
        String str = "abcde";
        process2(str.toCharArray(), 0);
    }

}
