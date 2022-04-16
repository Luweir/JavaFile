package Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test {

    public static boolean isNumberAndChar(String str) {
        // 正则匹配式有误
        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                flag1 = true;
            } else if (c >= '0' && c <= '9') {
                flag2 = true;
            } else {
                return false;
            }
        }
        return flag1 && flag2;
    }

    public static void main(String[] args) {
    }
}