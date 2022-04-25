package Test;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Integer x = 123;
        Integer y = 123;
        System.out.println(x == y);    // true
        Integer z = 129;
        Integer k = 129;
        System.out.println(z == k);   // false
    }
}