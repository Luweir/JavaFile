package Test;

import java.util.*;

public class Test {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        list.sort((t1, t2) ->
                t1.toString().compareTo(t2.toString())
        );
        return list;
    }



    public static void main(String[] args) {
        Test test = new Test();
        List<Integer> res = test.lexicalOrder(13);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}